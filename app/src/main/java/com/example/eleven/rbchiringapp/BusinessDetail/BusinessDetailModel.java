package com.example.eleven.rbchiringapp.BusinessDetail;

import com.example.eleven.rbchiringapp.Models.BusinessDetail;
import com.example.eleven.rbchiringapp.Models.Review;
import com.example.eleven.rbchiringapp.Models.YelpReviews;
import com.example.eleven.rbchiringapp.Retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eleven on 10/5/2016.
 */
public class BusinessDetailModel implements BusinessDetailModelInterface {




    @Override
    public void fetchBusinessDetail(final OnDetailFinishedListener listener, String id) {
        Call<BusinessDetail> call = RetrofitManager.get_publicAPI().getYelpBusinessDetail(id);
        call.enqueue(new Callback<BusinessDetail>(){
            @Override
            public void onResponse(Call<BusinessDetail> call, Response<BusinessDetail> response) {
                if(response.isSuccessful()){
                    listener.onDetailFinished(response.body());
                }
            }

            @Override
            public void onFailure(Call<BusinessDetail> call, Throwable t) {

            }
        });
    }

    @Override
    public void fetchBusinessReview(final OnReviewFinishedListener listener, String id) {

        Call<YelpReviews> call = RetrofitManager.get_publicAPI().getYelpReview(id);
        call.enqueue(new Callback<YelpReviews>(){
            @Override
            public void onResponse(Call<YelpReviews> call, Response<YelpReviews> response) {
                if(response.isSuccessful()){
                    listener.onReviewFinished(response.body());
                }
            }

            @Override
            public void onFailure(Call<YelpReviews> call, Throwable t) {

            }
        });

    }
}
