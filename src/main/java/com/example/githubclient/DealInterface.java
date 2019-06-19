package com.example.githubclient;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

public interface DealInterface {

    @FormUrlEncoded
    @POST("/v1/deals")
    Call<Map<String, Object>> postDeal(@Query("api_token") String api_token,
                                  @Field("title") String title,
                                  @Field("org_id") int org_id);
}
