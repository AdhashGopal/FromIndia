package com.app.fromindia.retrofit;

import com.app.fromindia.model.LoginResponse;
import com.app.fromindia.model.SignUpResp;
import com.app.fromindia.model.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("rest/V1/mobiconnect/customer/register")
    Call<ArrayList<LoginResponse>> userRegistration(@Body JsonObject object);

    @POST("rest/V1/mobiconnect/customer/login")
    Call<ArrayList<LoginResponse>> userLogin(@Body JsonObject object);

    @POST("rest/V1/mobiconnect/customer/forgotpassword")
    Call<ArrayList<LoginResponse>> userForgotPassword(@Body JsonObject object);


}
