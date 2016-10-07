package com.example.eleven.rbchiringapp.BusinessDetail;

import com.example.eleven.rbchiringapp.Models.BusinessDetail;
import com.example.eleven.rbchiringapp.Models.YelpReviews;
import com.example.eleven.rbchiringapp.Models.YelpSearchResult;

/**
 * Created by Eleven on 10/5/2016.
 */
public interface BusinessDetailModelInterface {
    interface OnDetailFinishedListener {
        void onDetailFinished(BusinessDetail result
        );
    }

    void fetchBusinessDetail(OnDetailFinishedListener listener,String id);

    interface OnReviewFinishedListener {
        void onReviewFinished(YelpReviews result
        );
    }

    void fetchBusinessReview(OnReviewFinishedListener listener,String id);



}
