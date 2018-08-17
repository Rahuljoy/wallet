package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.LoginObject;
import com.example.rahul.wallet.model.LoginRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface LoginService {
    @Headers("Content-Type: application/json")
    @POST("login/login-api.php")
    Call<LoginObject>login(@Body LoginRequest loginRequest);
}
