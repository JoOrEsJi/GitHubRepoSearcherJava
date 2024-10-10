package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;

public class GitHubRepoSearcher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the GitHub user: ");
        String username = scanner.nextLine();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repository>> reposCall = service.listRepos(username);

        try {
            Response<List<Repository>> response = reposCall.execute();
            if (response.isSuccessful()) {
                List<Repository> repos = response.body();
                if (repos != null && !repos.isEmpty()) {
                    System.out.println("\n " + username + "'s repos:");
                    System.out.printf("%-30s %-50s %-10s\n", "Name", "Description", "Stargazers");
                    System.out.println("--------------------------------------------------------------------------------------");

                    for (Repository repo : repos) {
                        System.out.printf("%-30s %-50s %-10d\n", repo.getName(), repo.getDescription(), repo.getStargazersCount());
                    }
                } else {
                    System.out.println("Specified user has no repos.");
                }
            } else {
                System.out.println("Error getting user's repos. Verify the username and try again.");
            }
        } catch (IOException e) {
            System.out.println("Problem communicating with the API.");
            e.printStackTrace();
        }
    }
}