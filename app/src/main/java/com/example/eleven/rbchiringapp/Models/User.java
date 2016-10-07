package com.example.eleven.rbchiringapp.Models;

/**
 * Created by Eleven on 10/5/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class User {

    @SerializedName("image_url")
    @Expose
    private Object imageUrl;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     *
     * @return
     * The imageUrl
     */
    public Object getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     * The image_url
     */
    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
