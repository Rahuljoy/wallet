package com.example.rahul.wallet.model;

public class AddCardRequest {

    private String user_id;
    private String card_no;
    private String expair_date;
    private String full_name;
    private String c_v_s_code;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getExpair_date() {
        return expair_date;
    }

    public void setExpair_date(String expair_date) {
        this.expair_date = expair_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getC_v_s_code() {
        return c_v_s_code;
    }

    public void setC_v_s_code(String c_v_s_code) {
        this.c_v_s_code = c_v_s_code;
    }
}
