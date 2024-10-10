package org.example;

public class Repository {
    private String name;
    private String description;
    private int stargazers_count;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description != null ? description : "No description";
    }

    public int getStargazersCount() {
        return stargazers_count;
    }
}