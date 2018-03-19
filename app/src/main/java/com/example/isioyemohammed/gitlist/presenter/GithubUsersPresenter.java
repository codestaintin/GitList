package com.example.isioyemohammed.gitlist.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.isioyemohammed.gitlist.GitListAdapter;
import com.example.isioyemohammed.gitlist.model.GithubUsers;
import com.example.isioyemohammed.gitlist.model.GithubUsersResponse;
import com.example.isioyemohammed.gitlist.service.GithubService;

import java.util.List;

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
    Context context;

    /**
     * Class method for provided context to the presenter.
     * @param context - Instance of Context
     */
    public GithubUsersPresenter(Context context) {
        this.context = context;
        if (this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    /**
     * Method for getting list of users from server and populating the recyclerView.
     * @param recyclerView - recyclerView
     */
    public void getGitHubUsers(final RecyclerView recyclerView) {
        githubService
                .getAPI()
                .getItems()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GithubUsersResponse> call,
                                           @NonNull Response<GithubUsersResponse> response) {
                        List<GithubUsers> githubUsersResponse = response.body().getItems();
                        if (githubUsersResponse != null) {
                            GitListAdapter adapter = new GitListAdapter(githubUsersResponse,
                                    context);
                            recyclerView.setAdapter(adapter);
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
