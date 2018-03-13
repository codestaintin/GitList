package com.example.isioyemohammed.gitlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by isioyemohammed on 12/03/2018.
 */

public class GithubUsersResponse {

    @SerializedName("login")
    private List<GithubUsers> gitUsers;

    public List<GithubUsers> getGitUsers() {
        return gitUsers;
    }
}
