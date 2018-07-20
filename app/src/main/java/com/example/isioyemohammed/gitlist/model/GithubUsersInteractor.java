package com.example.isioyemohammed.gitlist.model;

import android.support.annotation.NonNull;

import com.example.isioyemohammed.gitlist.presenter.GithubUsersContract;
import com.example.isioyemohammed.gitlist.service.GithubService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by isioyemohammed on 18/07/2018.
 * Gitlist
 */

public class GithubUsersInteractor implements GithubUsersContract.Repository {
    /**
     * GithubService object.
     */
    GithubService githubService = new GithubService();

    @Override
    public void queryApi(final Callback callback) {
        githubService.getAPI()
                .getItems()
                .enqueue(new retrofit2.Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GithubUsersResponse> call,
                                           @NonNull Response<GithubUsersResponse> response) {
                        GithubUsersResponse userResponse = response.body();
                        ArrayList<GithubUsers> githubUsersResponse;
                        assert userResponse != null;
                        githubUsersResponse = userResponse.getItems();
                        if (githubUsersResponse != null) {
                            callback.onFinish(githubUsersResponse);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GithubUsersResponse> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }
}
