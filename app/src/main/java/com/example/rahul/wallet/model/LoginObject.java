package com.example.rahul.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginObject {

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_active")
    @Expose
    private String user_active;


    public String getUser_id() {
        return user_id;
    }

    public String getUser_active() {
        return user_active;
    }
}
