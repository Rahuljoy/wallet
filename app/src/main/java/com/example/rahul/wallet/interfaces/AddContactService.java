package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.AddContactObject;
import com.example.rahul.wallet.model.AddContactRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddContactService {
    @Headers("Content-Type: application/json")
    @POST("transaction/beneficiary-fundtransfer-api.php")
    Call<AddContactObject> addAccountContact(@Query("user_id") String user_id , @Body AddContactRequest addContactRequest);

}
