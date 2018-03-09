package com.example.isioyemohammed.gitlist;

/**
 * Created by isioyemohammed on 07/03/2018.
 */

public class UserList {
    private String username;
    private String company;

    /**
     *
     * @param username
     * @param company
     */
    public UserList(String username, String company) {
        this.username = username;
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public String getCompany() {
        return company;
    }


}
