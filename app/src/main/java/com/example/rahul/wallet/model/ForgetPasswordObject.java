package com.example.rahul.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordObject {

    @SerializedName("e_mail")
    @Expose
    private String e_mail;

    public String getE_mail() {
        return e_mail;
    }

}
