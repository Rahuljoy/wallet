package com.example.rahul.wallet.interfaces;

import com.example.rahul.wallet.model.FundTransactionObject;
import com.example.rahul.wallet.model.FundTransactionRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FundTransactionService {
    @Headers("Content-Type: application/json")
    @POST("transaction/transaction-fund-api.php")
    Call<FundTransactionObject> fundtrunsaction(@Query("user_id") String user_id , @Body FundTransactionRequest fundTransactionRequest);
}
