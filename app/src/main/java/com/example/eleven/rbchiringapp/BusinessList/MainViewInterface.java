package com.example.eleven.rbchiringapp.BusinessList;

import com.example.eleven.rbchiringapp.Models.Business;
import com.example.eleven.rbchiringapp.Models.YelpSearchResult;

/**
 * Created by Eleven on 10/5/2016.
 */
public interface MainViewInterface {
    void setadapter(YelpSearchResult result);

    void setcategorizedadapter(YelpSearchResult result);

    boolean check_checkbox();
}
