package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.ViewCardObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ViewCardService {

    @GET("card/view-card-api.php")
    Call<ViewCardObject> cardInformation(@Query("user_id") String user_id);

}
