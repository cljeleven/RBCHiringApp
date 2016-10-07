package com.example.eleven.rbchiringapp.Models;

/**
 * Created by Eleven on 10/6/2016.
 */
public class categorized_business {

    private Business business = null;
    private Category category= null;
    private int category_size=0;
    public void setBusiness(Business business){
        this.business = business;
    }
    public void setCategory(Category category){
        this.category = category;
    }
    public Business getBusiness(){
        return this.business;
    }
    public Category getCategory(){
        return this.category;
    }
    public void setCategory_size(int size){
        this.category_size = size;
    }
    public int getCategory_size(){
        return this.category_size;
    }
}
