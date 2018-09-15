package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.DashboardObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DashboardService {
@GET ("dashboard/user-api.php")
Call<DashboardObject>userInformation(@Query("user_id") String user_id);
}
