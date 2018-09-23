package com.example.rahul.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCardObject {

    @SerializedName("card_type")
    @Expose
    private String card_type;

    @SerializedName("balance")
    @Expose
    private String balance;

    @SerializedName("card_pin")
    @Expose
    private String card_pin;

    public String getCard_type() {
        return card_type;
    }

    public String getBalance() {
        return balance;
    }

    public String getCard_pin() {
        return card_pin;
    }
}
