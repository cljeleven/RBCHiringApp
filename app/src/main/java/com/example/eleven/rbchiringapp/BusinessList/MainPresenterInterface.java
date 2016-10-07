package com.example.eleven.rbchiringapp.BusinessList;

import android.os.Bundle;

import com.example.eleven.rbchiringapp.Models.Business;

/**
 * Created by Eleven on 10/5/2016.
 */
public interface MainPresenterInterface {

    void set_sort(String sort);
    void searchresult(String term,
                      String location
                  );
    public void onItemClicked(Business business) ;
    void onSaveInstanceState(Bundle outState);
}
