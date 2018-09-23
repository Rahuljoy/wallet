package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.AddCardObject;
import com.example.rahul.wallet.model.AddCardRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddCardService {
    @Headers("Content-Type: application/json")
    @POST("card/add-card-api.php")
    Call<AddCardObject> addCard(@Query("user_id") String user_id , @Body AddCardRequest addCardRequest);
}
