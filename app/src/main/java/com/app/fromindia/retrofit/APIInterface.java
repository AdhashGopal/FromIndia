package com.app.fromindia.retrofit;

import com.app.fromindia.model.SignUpResp;
import com.app.fromindia.model.User;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @Headers("Content-Type: application/json")
    @POST("integration/customer/token")
    Call<ResponseBody> userLogin(@Body JsonObject object);

    @POST("customers")
    Call<ResponseBody> createUser(@Body JsonObject object);
}
