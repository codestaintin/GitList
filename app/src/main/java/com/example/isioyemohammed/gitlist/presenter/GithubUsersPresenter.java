package com.example.isioyemohammed.gitlist.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.isioyemohammed.gitlist.model.GithubUsers;
import com.example.isioyemohammed.gitlist.model.GithubUsersResponse;
import com.example.isioyemohammed.gitlist.service.GithubService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by isioyemohammed on 14/03/2018.
 */

public class GithubUsersPresenter {
    /**
     * GithubService - creates an instance of GithubService.
     */
    GithubService githubService;
    /**
     * Context - creates an instance of Context.
     */
    ViewUsers view;

    /**
     * Class method for provided context to the presenter.
     * @param view - Instance of Context
     */
    public GithubUsersPresenter(ViewUsers view) {
        this.view = view;
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    /**
     * View interface.
     */
    public interface ViewUsers {
        /**
         * displayGithubUsers interface method.
         *
         * @param developerList - method parameter
         */
        void displayGithubUsers(ArrayList<GithubUsers> developerList);
    }

    /**
     * Method for getting list of users from server and populating the recyclerView.
     */
    public void getGitHubUsers() {
        githubService
                .getAPI()
                .getItems()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GithubUsersResponse> call,
                                           @NonNull Response<GithubUsersResponse> response) {
                        GithubUsersResponse userResponse = response.body();
                        ArrayList<GithubUsers> githubUsersResponse;
                        assert userResponse != null;
                        githubUsersResponse = userResponse.getItems();
                        if (githubUsersResponse != null) {
                            view.displayGithubUsers(githubUsersResponse);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GithubUsersResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Oops!! Something went wrong!");
                        } catch (InterruptedException e) {
                            Log.e("ERROR", e + "An error occurred");
                        }
                    }
                });
    }
}
