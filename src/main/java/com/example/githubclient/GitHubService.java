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

    private String accessToken;

    private GistInterface service;

    public GitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL_GH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GistInterface.class);
        this.accessToken = "token " + "dbfa0091d842781b534f965b81115eda4323d4fb";
    }

    public List<Gist> getPublicGists(String username) throws IOException {
        Call<List<Gist>> retrofitCall = service.listGists(accessToken, API_VERSION_SPEC_GH, username);

        Response<List<Gist>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }
}