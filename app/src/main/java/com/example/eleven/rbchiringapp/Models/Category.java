package com.example.eleven.rbchiringapp.Models;

/**
 * Created by Eleven on 10/5/2016.
 */



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Category implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("alias")
    @Expose
    private String alias;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     *
     * @param alias
     * The alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }


    @Override
    public int hashCode() {
        return 101+alias.length()+title.length();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (!alias .equals(other.alias))
            return false;
        return true;
    }
}