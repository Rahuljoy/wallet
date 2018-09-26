package com.example.rahul.wallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileRechargeObject {
    @SerializedName("transaction_status")
    @Expose
    private String transaction_status;

    public String getTransaction_status() {
        return transaction_status;
    }
}
