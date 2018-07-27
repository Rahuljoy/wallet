package com.example.rahul.wallet.remote;

import com.example.rahul.wallet.model.ResourceObject;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {

//    @POST("(username)/(password)")
//    Call<ResourceObject> login(@Path("username") String username , @Path("password") String password);

//    Call<ResourceObject> login(@Body('username') String username,@Body('password') String password);

    @POST("user/login.json")
    Call<ResourceObject>login(@Header("Content-Type") String contentType, @Header("") String key , @Body UserService userService);
}
