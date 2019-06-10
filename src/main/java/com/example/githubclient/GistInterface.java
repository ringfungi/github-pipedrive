package com.example.githubclient;

import org.eclipse.egit.github.core.Gist;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.List;

public interface GistInterface {

    @GET("/users/{username}/gists")
    Call<List<Gist>> listGists(@Header("Authorization") String accessToken,
                               @Header("Accept") String apiVersionSpec,
                               @Path("username") String username);
}
