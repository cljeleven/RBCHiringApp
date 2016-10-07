package com.example.eleven.rbchiringapp.BusinessList;

import com.example.eleven.rbchiringapp.Models.YelpReviews;
import com.example.eleven.rbchiringapp.Models.YelpSearchResult;

/**
 * Created by Eleven on 10/5/2016.
 */
public interface MainModelInterface {
    interface OnFinishedListener {
        void onFinished(YelpSearchResult result
                       );
    }

    void fetchdata(OnFinishedListener listener,
                   String term,
                   String location,
                   int limit,
                   String sort_by);


}
