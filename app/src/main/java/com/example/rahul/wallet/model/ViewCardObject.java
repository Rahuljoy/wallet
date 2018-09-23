package com.example.rahul.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewCardObject {

    @SerializedName("card_type")
    @Expose
    private String card_type;

    @SerializedName("full_name")
    @Expose
    private String full_name;

    @SerializedName("balance")
    @Expose
    private String balance;

    @SerializedName("expair_date")
    @Expose
    private String expair_date;


    public String getCard_type() {
        return card_type;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getBalance() {
        return balance;
    }

    public String getExpair_date() {
        return expair_date;
    }
}
