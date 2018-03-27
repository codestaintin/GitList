package com.example.isioyemohammed.gitlist.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.isioyemohammed.gitlist.GitListAdapter;
import com.example.isioyemohammed.gitlist.presenter.GithubUsersPresenter;
import com.example.isioyemohammed.gitlist.R;
import com.example.isioyemohammed.gitlist.model.GithubUsers;
import com.example.isioyemohammed.gitlist.utils.NetworkUtility;

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
    /**
     * ConstraintLayout variable.
     */
    ConstraintLayout constraintLayout;
    /**
     * NetworkUtility variable.
     */
    private NetworkUtility networkUtility;

    /**
     * BroadcastReceiver to check for internet access.
     */
    private BroadcastReceiver broadcastReceiver;

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
        constraintLayout = findViewById(R.id.constraint_layout);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (networkUtility == null) {
            networkUtility = new NetworkUtility(getApplicationContext());
        }

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (networkUtility.isConnected()) {
                    presenter.getGitHubUsers();
                }
                Log.d("MainActivity", "Broadcast reciever was callled");
            }
        };

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing() && !networkUtility.isConnected()) {
                    swipeRefreshLayout.setRefreshing(false);
                    displayNetworkError();
                }
                displayGithubUsers(userLists);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        if (savedInstanceState != null) {
            userLists = savedInstanceState.getParcelableArrayList(DEVELOPER_LIST);
            displayGithubUsers(userLists);
        } else {
            if (networkUtility.isConnected()) {
                showProgressBar();
                presenter.getGitHubUsers();
            } else {
                displayNetworkError();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * displayNetworkError method.
     */
    private void displayNetworkError() {
        /**
         * Snackbar instance.
         */
        Snackbar.make(constraintLayout, "No Network please try again!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.getGitHubUsers();
                    }
                })
                .show();
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
