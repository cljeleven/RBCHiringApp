package com.example.eleven.rbchiringapp.Retrofit;

import com.example.eleven.rbchiringapp.Models.BusinessDetail;
import com.example.eleven.rbchiringapp.Models.YelpReviews;
import com.example.eleven.rbchiringapp.Models.YelpSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Eleven on 10/5/2016.
 */
public interface  RetrofitServices {
    @GET("/v3/businesses/search")
    Call<YelpSearchResult> YelpSearch(@Query("term")String term,
                                      @Query("location")String location,
                                      @Query("limit") int limit,
                                      @Query("sort_by")String sort_by);


    @GET("/v3/businesses/{id}/reviews")
    Call<YelpReviews> getYelpReview(@Path("id")String id);

    @GET("/v3/businesses/{id}")
    Call<BusinessDetail>getYelpBusinessDetail(@Path("id")String id);





}
