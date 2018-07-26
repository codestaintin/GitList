package com.example.isioyemohammed.gitlist.service;

import com.example.isioyemohammed.gitlist.model.GithubAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by isioyemohammed on 13/03/2018.
 */

public class GithubService {
    /**
     * Creates an instance of retrofit.
     */
    private Retrofit retrofit = null;

    /**
     * Calls getAPI method in the Github API class.
     *
     * @return getAPI instance
     */
    public GithubAPI getAPI() {
        /**
         * BaseUrl -  for API calls
         */
        String baseUrl = "https://api.github.com/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GithubAPI.class);
    }
}
