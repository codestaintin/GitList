package com.example.isioyemohammed.gitlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by isioyemohammed on 12/03/2018.
 */

public class GithubUsersResponse {
    @SerializedName("items")
    private List<GithubUsers> items;

    public List<GithubUsers> getItems() {
        return items;
    }

}
