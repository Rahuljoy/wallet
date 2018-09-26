package com.example.rahul.wallet.model;

public class MobileRechargeRequest {
    private String user_id;
    private String contact_no;
    private String transactions_amount;
    private String user_password;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getTransactions_amount() {
        return transactions_amount;
    }

    public void setTransactions_amount(String transactions_amount) {
        this.transactions_amount = transactions_amount;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
