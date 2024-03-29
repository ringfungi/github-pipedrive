package com.example.githubclient;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Map;

@Service
public class PipedriveService implements APIConfiguration{

    private String APIToken;
    private String[] paramArray;
    private DealInterface service;

    public PipedriveService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL_PD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DealInterface.class);

        this.APIToken = "1ec14171ac95d8b67eeda5b3ec266fe136ece8df";

    }

    void postNewDeal(String dealTitle, int orgID) throws IOException {
        Call<Map<String, Object>> retrofitCall = service.postDeal(APIToken, dealTitle, orgID);

        Response<Map<String, Object>> response = retrofitCall.execute();

        System.out.println(response.raw());


    }
}
