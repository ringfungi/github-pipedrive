package com.example.githubclient;

import org.eclipse.egit.github.core.Gist;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Service
public class GitHubService implements APIConfiguration {

    private String currentUsername;

    private int firstSubmemberIndex = 0;

    private String accessToken;

    private GistInterface service;

    public GitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL_GH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GistInterface.class);
        this.accessToken = "token " + "2148919fbcf2a763582d9f300fab23f36deffca8";
    }

    public List<Gist> getPublicGists(String username) throws IOException {
        Call<List<Gist>> retrofitCall = service.listGists(accessToken, API_VERSION_SPEC_GH, username);

        Response<List<Gist>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        currentUsername = username;
        //System.out.println(response.getClass().getDeclaredFields();

        //System.out.println(response.body().get(0).getDescription());
        return response.body();
    }

    public List<Gist> getNewPublicGists(String username) throws IOException {

        if (!currentUsername.equals(username)){
            firstSubmemberIndex = 0;
            return null;
        }

        Call<List<Gist>> retrofitCall = service.listGists(accessToken, API_VERSION_SPEC_GH, username);

        Response<List<Gist>> response = retrofitCall.execute();

        return response.body().subList(firstSubmemberIndex, response.body().size()-1    );

        //System.out.println(response.getClass().getDeclaredFields();

        //System.out.println(response.body().get(0).getDescription());
    }
}