package com.example.isioyemohammed.gitlist;

/**
 * Created by isioyemohammed on 07/03/2018.
 */

public class UserList {
    /**
     * String username - username.
     */
    private String username;
    /**
     * String company - company.
     */
    private String company;
    /**
     * String gitUrl - gitUrl.
     */
    private String gitUrl;

    /**
     * Constructor for class fields.
     * @param username - username
     * @param company - company
     * @param gitUrl - gitUrl
     */
    public UserList(String username, String company, String gitUrl) {
        this.username = username;
        this.company = company;
        this.gitUrl = gitUrl;
    }

    /**
     * Getter method for username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method for company.
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Getter method for gitUrl.
     * @return gitUrl
     */
    public String getGitUrl() {
        return gitUrl;
    }
}
