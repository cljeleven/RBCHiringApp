package com.example.eleven.rbchiringapp.BusinessDetail;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.eleven.rbchiringapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eleven on 10/6/2016.
 */
public class customerPagerAdp  extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<String> Photos;
    public customerPagerAdp(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.Photos = new ArrayList<String>();
    }

    @Override
    public int getCount() {

        return (Photos.size()<10)?Photos.size():10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.viewpager_cell, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.viewpager_img);
        Picasso.with(mContext)
                .load(Photos.get(position))
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public void setPhotos(List<String> Photos){
        if(this.Photos.size()>0){
            this.Photos.clear();
        }
        addPhotos(Photos);
    }
    public void addPhotos(List<String> Photos){
        this.Photos.addAll(Photos);
        notifyDataSetChanged();
    }
}