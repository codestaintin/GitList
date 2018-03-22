package com.example.isioyemohammed.gitlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by isioyemohammed on 12/03/2018.
 */

public class GithubUsersResponse {
    /**
     * Item - Items parameter.
     */
    @SerializedName("items")
    private ArrayList<GithubUsers> items;

    /**
     * Getter method for items.
     * @return items
     */
    public ArrayList<GithubUsers> getItems() {
        return items;
    }

}
