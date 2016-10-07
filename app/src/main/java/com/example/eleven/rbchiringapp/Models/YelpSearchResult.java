package com.example.eleven.rbchiringapp.Models;

/**
 * Created by Eleven on 10/5/2016.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class YelpSearchResult {


    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("businesses")
    @Expose
    private List<Business> businesses = new ArrayList<Business>();

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The businesses
     */
    public List<Business> getBusinesses() {
        return businesses;
    }

    /**
     *
     * @param businesses
     * The businesses
     */
    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }
}
