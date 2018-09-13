package com.example.rahul.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardObject {


   @SerializedName("user_name")
   @Expose
   private String user_name;

    @SerializedName("user_active")
    @Expose
    private String user_active;

    @SerializedName("user_account_no")
    @Expose
    private String user_account_no;

    @SerializedName("contace_no")
    @Expose
    private String contace_no;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("picture_type")
    @Expose
    private String picture_type;

    @SerializedName("picture_path")
    @Expose
    private String picture_path;

    @SerializedName("balance")
    @Expose
    private String balance;

    public String getUser_name() {
        return user_name;
    }

    public String getUser_active() {
        return user_active;
    }

    public String getUser_account_no() {
        return user_account_no;
    }

    public String getContace_no() {
        return contace_no;
    }

    public String getImage() {
        return image;
    }

    public String getPicture_type() {
        return picture_type;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public String getBalance() {
        return balance;
    }
}
