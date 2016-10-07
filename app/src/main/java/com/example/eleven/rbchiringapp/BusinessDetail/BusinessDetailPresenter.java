package com.example.eleven.rbchiringapp.BusinessDetail;

import com.example.eleven.rbchiringapp.Models.BusinessDetail;
import com.example.eleven.rbchiringapp.Models.YelpReviews;

/**
 * Created by Eleven on 10/5/2016.
 */
public class BusinessDetailPresenter implements BusinessDetailPresenterInterface,BusinessDetailModelInterface.OnDetailFinishedListener,BusinessDetailModelInterface.OnReviewFinishedListener {
    BusinessDetailViewInterface businessDetailViewInterface;
    BusinessDetailModelInterface businessDetailModelInterface;

    public BusinessDetailPresenter(BusinessDetailViewInterface businessDetailViewInterface,
                                   BusinessDetailModelInterface businessDetailModelInterface){
        this.businessDetailModelInterface = businessDetailModelInterface;
        this.businessDetailViewInterface = businessDetailViewInterface;

    }

    @Override
    public void onDetailFinished(BusinessDetail result) {
        businessDetailViewInterface.updateImg(result.getPhotos());
    }
    @Override
    public void onReviewFinished(YelpReviews result) {
    }
    @Override
    public void fetchdetail(String id) {
        businessDetailModelInterface.fetchBusinessDetail(this,id);
    }

    @Override
    public void fetchreview(String id) {
        businessDetailModelInterface.fetchBusinessReview(this,id);
    }


}
