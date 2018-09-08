package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.ForgetPasswordObject;
import com.example.rahul.wallet.model.ForgetPasswordRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface ForgetPasswordService {
    @Headers("Content-Type: application/json")
    @POST("login/forget-password-api.php")
    Call<ForgetPasswordObject> recoverPassword(@Body ForgetPasswordRequest forgetPasswordRequest);
}
