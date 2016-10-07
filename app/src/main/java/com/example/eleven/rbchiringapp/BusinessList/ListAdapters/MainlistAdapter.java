package com.example.eleven.rbchiringapp.BusinessList.ListAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eleven.rbchiringapp.BusinessList.MainPresenter;
import com.example.eleven.rbchiringapp.BusinessList.MainPresenterInterface;
import com.example.eleven.rbchiringapp.BusinessList.MainViewInterface;
import com.example.eleven.rbchiringapp.Models.Business;
import com.example.eleven.rbchiringapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eleven on 10/5/2016.
 */
public class MainlistAdapter extends RecyclerView.Adapter<MainlistAdapter.ViewHolder> {
    List<Business> businessList;
    MainViewInterface mainViewInterface;
    Context context;
    MainPresenterInterface mainPresenterInterface;


    public MainlistAdapter(Context context, MainPresenter mainPresenterInterface){
        this.context = context;
        this.mainPresenterInterface = mainPresenterInterface;
        this.businessList = new ArrayList<>();
    }


    @Override
    public MainlistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlist_cell,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainlistAdapter.ViewHolder holder, int position) {

        final Business current_business = businessList.get(position);
        if(current_business.getImageUrl()!=null){
            Picasso.with(context)
                    .load(current_business.getImageUrl())
                    .into(holder.restaurant_img);
        }

        holder.restaurant_rating.setText("rate: "+current_business.getRating());
        holder.restaurant_price.setText(current_business.getPrice());
        holder.restaurant_name.setText(current_business.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenterInterface.onItemClicked(current_business);
            }
        });


    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder {

        ImageView restaurant_img;
        TextView restaurant_name;
        TextView restaurant_rating;
        TextView restaurant_price;
        public ViewHolder(View itemView) {
            super(itemView);

            restaurant_img = (ImageView)itemView.findViewById(R.id.mainlist_cell_restaurant_img);
            restaurant_name = (TextView)itemView.findViewById(R.id.mainlist_cell_restaurant_name);
            restaurant_rating = (TextView)itemView.findViewById(R.id.mainlist_cell_restaurant_rate);
            restaurant_price = (TextView) itemView.findViewById(R.id.mainlist_cell_restaurant_price);

        }
    }


    public void setList(List<Business> businessList){
        if(this.businessList.size()>0){
            this.businessList.clear();
        }
        addList(businessList);
    }

    public void addList(List<Business>businessList){
        this.businessList.addAll(businessList);
        notifyDataSetChanged();
    }


}
