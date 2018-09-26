package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.ContactNoObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContactNoService {

    @GET("transaction/transaction-contact-api.php")
    Call<ContactNoObject> contactNo(@Query("user_id") String user_id);
}
