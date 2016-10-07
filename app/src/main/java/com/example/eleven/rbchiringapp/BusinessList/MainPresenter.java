package com.example.eleven.rbchiringapp.BusinessList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.eleven.rbchiringapp.BusinessDetail.BusinessDetailActivity;
import com.example.eleven.rbchiringapp.Models.Business;
import com.example.eleven.rbchiringapp.Models.YelpSearchResult;

/**
 * Created by Eleven on 10/5/2016.
 */
public class MainPresenter implements MainPresenterInterface,MainModelInterface.OnFinishedListener {

    MainViewInterface viewInterface;
    MainModelInterface modelInterface;
    String sort_by="best_match";
    String location = "Toronto";
    String term = "Ethiopian";
    int limit = 10;
    Context context;




    public MainPresenter(Context context,MainViewInterface viewInterface, MainModelInterface modelInterface){
        this.viewInterface = viewInterface;
        this.modelInterface = modelInterface;
        this.context=context;
    }

    @Override
    public void set_sort(String sort) {
        this.sort_by = sort;
        searchresult(term,location);
    }

    @Override
    public void searchresult(String term, String location) {
        this.term = term;
        this.location = location;
        modelInterface.fetchdata(this,term,location,limit,sort_by);
    }

    @Override
    public void onFinished(YelpSearchResult result) {

        if(viewInterface.check_checkbox()){
            viewInterface.setcategorizedadapter(result);

        }else {

            viewInterface.setadapter(result);
        }
    }


    @Override
    public void onItemClicked(Business business) {
        Intent intent = new Intent(context, BusinessDetailActivity.class);
        intent.putExtra("business",business);
        context.startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }



}
