package com.example.isioyemohammed.gitlist.model;

import com.example.isioyemohammed.gitlist.service.GithubService;
import com.example.isioyemohammed.gitlist.users.GithubUsersContract;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by isioyemohammed on 13/03/2018.
 */

public class GithubUsersInteractor implements GithubUsersContract.Repository {
    /**
     * GithubService object.
     */
    GithubService githubService = new GithubService();
    /**
     * Github users.
     */
    private ArrayList<GithubUsers> githubUsers;

    @Override
    public void queryApi(final Callback callback) {
        githubService
                .getAPI()
                .getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<GithubUsersResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // onSubscribe method
                    }

                    @Override
                    public void onNext(GithubUsersResponse githubUsersResponse) {
                        assert githubUsersResponse != null;
                        githubUsers = githubUsersResponse.getItems();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callback.onFinish(githubUsers);
                    }
                });
    }
}
