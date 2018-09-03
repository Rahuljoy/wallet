package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.ForgetPasswordObject;
import com.example.rahul.wallet.model.ForgetPasswordRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ForgetPasswordService {
    @Headers("Content-Type: application/json")
    @POST("login/forget-password-api.php")
    Call<ForgetPasswordObject> resetPassword(@Body ForgetPasswordRequest forgetPasswordRequest);
}
