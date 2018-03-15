package com.example.isioyemohammed.gitlist.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

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
    GithubService githubService;
    Context context;

    public GithubUsersPresenter(Context context) {
        this.context = context;
        if(this.githubService == null) {
            this.githubService = new GithubService();
        }
    }

    public void getGitHubUsers(final RecyclerView recyclerView) {
        githubService
                .getAPI()
                .getItems()
                .enqueue(new Callback<GithubUsersResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GithubUsersResponse> call, @NonNull Response<GithubUsersResponse> response) {
                        List<GithubUsers> githubUsersResponse = response.body().getItems();
                        if( githubUsersResponse != null ) {
                            GitListAdapter adapter = new GitListAdapter(githubUsersResponse, context);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUsersResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Oops!! Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
