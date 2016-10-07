package com.example.eleven.rbchiringapp.BusinessList;

import com.example.eleven.rbchiringapp.Models.YelpSearchResult;
import com.example.eleven.rbchiringapp.Retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eleven on 10/5/2016.
 */
public class MainModel implements MainModelInterface{
    @Override
    public void fetchdata(final OnFinishedListener listener,
                          String term,
                          String location,
                          int limit,
                          String sort_by) {

        /*fetch data from yelp server*/
        Call<YelpSearchResult> call = RetrofitManager.get_publicAPI().YelpSearch(term, location,limit,sort_by);
        call.enqueue(new Callback<YelpSearchResult>() {
            @Override
            public void onResponse(Call<YelpSearchResult> call, Response<YelpSearchResult> response) {
                if(response.isSuccessful()){
                    listener.onFinished(response.body());
                }
                /*add error check here*/
            }

            @Override
            public void onFailure(Call<YelpSearchResult> call, Throwable t) {
                /*add error check here*/
            }
        });
    }





}
