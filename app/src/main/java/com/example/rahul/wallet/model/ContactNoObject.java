package com.example.rahul.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactNoObject {

    @SerializedName("contactno")
    @Expose
    private String contactno;

    public String getContactno() {
        return contactno;
    }

}
