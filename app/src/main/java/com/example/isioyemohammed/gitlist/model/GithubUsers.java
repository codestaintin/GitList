package com.example.isioyemohammed.gitlist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isioyemohammed on 12/03/2018.
 */

public class GithubUsers {
    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatar_url;

    @SerializedName("url")
    private String url;


    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getUrl() { return url; }
}
