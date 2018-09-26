package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.MobileRechargeObject;
import com.example.rahul.wallet.model.MobileRechargeRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MobileRechargeService {
    @Headers("Content-Type: application/json")
    @POST("transaction/mobile-recharge-api.php")
    Call<MobileRechargeObject> mobilerecharge(@Query("user_id") String user_id , @Body MobileRechargeRequest mobileRechargeRequest);
}
