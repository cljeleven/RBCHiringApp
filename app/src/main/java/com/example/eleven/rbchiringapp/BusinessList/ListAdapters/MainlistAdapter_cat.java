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
import com.example.eleven.rbchiringapp.Models.Category;
import com.example.eleven.rbchiringapp.Models.categorized_business;
import com.example.eleven.rbchiringapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Eleven on 10/5/2016.
 */
public class MainlistAdapter_cat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Business> businessList;

    List<categorized_business> cblist;
    Context context;
    MainPresenterInterface mainPresenterInterface;
    private Map<Category, List<Integer>> map;

    final int TYPE_CATEGORY = 0;
    final int TYPE_BUSINESS = 1;

    public MainlistAdapter_cat(Context context, MainPresenter mainPresenterInterface){
        this.context = context;
        this.mainPresenterInterface = mainPresenterInterface;
        this.businessList = new ArrayList<>();
        this.cblist = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_BUSINESS:
                View v1 =  LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlist_cell,parent,false);
                return new ViewHolderBusiness(v1);

            case TYPE_CATEGORY:
                View v2 =  LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlist_cell_category,parent,false);
                return new ViewHolderHeader(v2);

            default:
                View v3 =  LayoutInflater.from(parent.getContext()).inflate(R.layout.mainlist_cell,parent,false);
                return new ViewHolderBusiness(v3);
        }

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(getItemViewType(position)){
            case TYPE_CATEGORY:
                ViewHolderHeader vhc = (ViewHolderHeader)holder;
                final Category category = cblist.get(position).getCategory();
                int num = cblist.get(position).getCategory_size();
                if(category!=null&&num!=0){
                    vhc.category.setText(category.getTitle()+" ("+num+")");
                }
                break;

            case TYPE_BUSINESS:
                ViewHolderBusiness vhb = (ViewHolderBusiness)holder;
                final Business current_business = cblist.get(position).getBusiness();
                if(current_business!=null) {
                    if (current_business.getImageUrl() != null) {
                        Picasso.with(context)
                                .load(current_business.getImageUrl())
                                .into(vhb.restaurant_img);
                    }

                    vhb.restaurant_rating.setText("rate: " + current_business.getRating());
                    vhb.restaurant_price.setText(current_business.getPrice());
                    vhb.restaurant_name.setText(current_business.getName());

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mainPresenterInterface.onItemClicked(current_business);
                        }
                    });
                }
                break;

            default:
                break;

        }


    }


    @Override
    public int getItemViewType(int position) {
        if(cblist.get(position).getBusiness()!=null){
            return TYPE_BUSINESS;
        }else {
            return TYPE_CATEGORY;
        }

    }

    @Override
    public int getItemCount() {
        return cblist.size();

    }
    public class ViewHolderHeader  extends RecyclerView.ViewHolder {
        TextView category;
        public ViewHolderHeader(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.mainlist_cell_category_text);

        }
    }
    public class ViewHolderBusiness  extends RecyclerView.ViewHolder {

        ImageView restaurant_img;
        TextView restaurant_name;
        TextView restaurant_rating;
        TextView restaurant_price;
        public ViewHolderBusiness(View itemView) {
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
        generatecblist(this.businessList);
        notifyDataSetChanged();
    }


    public void generatecblist(List<Business> businessList){

        /*group business first*/
        map =  new HashMap<Category,List<Integer>>();
        for(int i = 0; i < businessList.size(); i++){
            for (Category category:businessList.get(i).getCategories()){
                List<Integer> pointerlist = map.get(category);
                if(pointerlist==null){   /*found new category*/
                    pointerlist=new ArrayList<>();
                    pointerlist.add(i);
                    map.put(category,pointerlist);
                }else{                    /*found existing category*/
                    pointerlist.add(i);
                    map.put(category,pointerlist);
                }
            }
        }

        /*create the list for display*/
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            categorized_business cbc = new categorized_business();
            Category category = (Category)pair.getKey();
            cbc.setCategory(category);

            List<Integer> businesspointers = (List<Integer>)pair.getValue();
            cbc.setCategory_size(businesspointers.size());
            cblist.add(cbc);
            for(int i=0;i<businesspointers.size();i++){
                categorized_business cbb = new categorized_business();
                cbb.setBusiness(businessList.get(businesspointers.get(i)));
                cblist.add(cbb);
            }
            it.remove();
        }

    }
    public void clearMap(){
        this.map.clear();
    }


}
