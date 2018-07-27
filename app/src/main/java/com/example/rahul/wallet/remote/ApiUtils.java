package com.example.rahul.wallet.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://localhost/Online-Bank/Bank-api/api-file/login/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
