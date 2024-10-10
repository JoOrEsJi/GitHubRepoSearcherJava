package org.example;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{username}/repos")
    Call<List<Repository>> listRepos(@Path("username") String username);
}