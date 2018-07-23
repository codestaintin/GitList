package com.example.isioyemohammed.gitlist.users;

import com.example.isioyemohammed.gitlist.model.GithubUsers;
import com.example.isioyemohammed.gitlist.model.GithubUsersInteractor;

import java.util.ArrayList;

/**
 * Created by isioyemohammed on 14/03/2018.
 */

public class GithubUsersPresenter implements GithubUsersContract.Actions,
        GithubUsersContract.Repository.Callback {
    /**
     * GithubUsersContract view instance.
     */
    GithubUsersContract.MainView view;
    /**
     * GithubInteractor instance.
     */
    GithubUsersInteractor githubUsersInteractor;

    /**
     * Class method for provided context to the presenter.
     *
     * @param view - Instance of Context
     */
    public GithubUsersPresenter(GithubUsersContract.MainView view) {
        githubUsersInteractor = new GithubUsersInteractor();
        this.view = view;
    }


    /**
     * Method for getting list of users from server and populating the recyclerView.
     */
    @Override
    public void getGitHubUsers(Boolean status) {
        if (status) {
            view.showProgressBar();
        }
        githubUsersInteractor.queryApi(this);
    }

    @Override
    public void onFinish(ArrayList<GithubUsers> githubUsers) {
        view.displayGithubUsers(githubUsers);
        view.hideProgressBar();
        view.hideSwipeRefresh();
    }

    @Override
    public void onError(Throwable throwable) {
        view.showSnackBar("No Network connection!");
        view.hideProgressBar();
        view.hideSwipeRefresh();

    }
}
