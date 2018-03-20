package com.example.isioyemohammed.gitlist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isioyemohammed on 12/03/2018.
 */

public class GithubUsers {
    /**
     * Login - Login parameter.
     */
    @SerializedName("login")
    private String login;
    /**
     * Avatar Url - Avatar Url parameter.
     */
    @SerializedName("avatar_url")
    private String avatarUrl;
    /**
     * Url - url parameter.
     */
    @SerializedName("url")
    private String url;

    /**
     * Getter method for login.
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter method for avatar.
     * @return avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Getter method for url.
     * @return url
     */
    public String getUrl() {
        return url;
    }
}
