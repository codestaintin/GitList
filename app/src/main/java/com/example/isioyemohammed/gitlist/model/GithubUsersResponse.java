package com.example.isioyemohammed.gitlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by isioyemohammed on 12/03/2018.
 */

public class GithubUsersResponse {
    /**
     * Item - Items parameter.
     */
    @SerializedName("items")
    private List<GithubUsers> items;

    /**
     * Getter method for items.
     * @return items
     */
    public List<GithubUsers> getItems() {
        return items;
    }

}
