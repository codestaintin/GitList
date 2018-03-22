package com.example.isioyemohammed.gitlist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isioyemohammed on 12/03/2018.
 */

public class GithubUsers implements Parcelable {
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
     * GithubUsers constructor.
     * @param in - Parcel parameter
     */
    protected GithubUsers(Parcel in) {
        login = in.readString();
        avatarUrl = in.readString();
        url = in.readString();
    }

    /**
     * Creator object of type GithubUsers.
     */
    public static final Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {
        @Override
        public GithubUsers createFromParcel(Parcel in) {
            return new GithubUsers(in);
        }

        @Override
        public GithubUsers[] newArray(int size) {
            return new GithubUsers[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(avatarUrl);
        dest.writeString(url);
    }
}
