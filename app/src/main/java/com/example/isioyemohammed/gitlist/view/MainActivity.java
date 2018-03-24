package com.example.isioyemohammed.gitlist.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.isioyemohammed.gitlist.GitListAdapter;
import com.example.isioyemohammed.gitlist.presenter.GithubUsersPresenter;
import com.example.isioyemohammed.gitlist.R;
import com.example.isioyemohammed.gitlist.model.GithubUsers;

import java.util.ArrayList;

/**
 * Class MainActivity.
 */
public class MainActivity extends AppCompatActivity implements GithubUsersPresenter.ViewUsers {
    /**
     * List - List off github users.
     */
    public ArrayList<GithubUsers> userLists;
    /**
     * Presenter - GithubPresenter.
     */
    private GithubUsersPresenter presenter = new GithubUsersPresenter(this);
    /**
     * Static constant variable.
     */
    public static final String DEVELOPER_LIST = "listKey";
    /**
     * SwipeRefreshLayout variable.
     */
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * ProgressBar variable.
     */
    private ProgressBar progressBar;
    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> developerList) {
        userLists = developerList;
        if (userLists != null) {
            RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);

            RecyclerView.Adapter adapter = new GitListAdapter(userLists);
            mRecyclerView.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                displayGithubUsers(userLists);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        if (savedInstanceState != null) {
            userLists = savedInstanceState.getParcelableArrayList(DEVELOPER_LIST);
            displayGithubUsers(userLists);
        } else {
            showProgressBar();
            presenter.getGitHubUsers();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(DEVELOPER_LIST, userLists);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userLists = savedInstanceState.getParcelableArrayList(DEVELOPER_LIST);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
