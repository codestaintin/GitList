package com.example.isioyemohammed.gitlist;

/**
 * Created by isioyemohammed on 07/03/2018.
 */

public class UserList {
    private String username;
    private String company;
    private String gitUrl;

    /**
     *
     * @param username
     * @param company
     */
    public UserList(String username, String company, String gitUrl) {
        this.username = username;
        this.company = company;
        this.gitUrl = gitUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getCompany() {
        return company;
    }

    public String getGitUrl() {
        return gitUrl;
    }


}
