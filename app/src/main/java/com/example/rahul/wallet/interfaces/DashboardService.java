package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.DashboardObject;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DashboardService {
//    @Headers("Content-Type: application/json")
    @GET ("dashboard/user-api.php?user_id={user_id}")
//    @FormUrlEncoded
    Call<DashboardObject>userInformation(@Path("user_id") String user_id);
}
