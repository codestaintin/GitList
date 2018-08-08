package com.example.isioyemohammed.gitlist.users;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.isioyemohammed.gitlist.R;
import com.example.isioyemohammed.gitlist.adapters.GitListAdapter;
import com.example.isioyemohammed.gitlist.model.GithubUsers;
import com.example.isioyemohammed.gitlist.utils.NetworkUtility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class UserActivity.
 */
public class UserActivity extends AppCompatActivity implements GithubUsersContract.MainView {
    /**
     * Static constant variable.
     */
    public static final String DEVELOPER_LIST = "listKey";
    /**
     * Hello.
     */
    static final String MESSAGE = "No Network connection!";
    /**
     * List - List off github users.
     */
    public ArrayList<GithubUsers> userLists;
    /**
     * SwipeRefreshLayout variable.
     */
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * ConstraintLayout variable.
     */
    @BindView(R.id.constraint_layout)
    ConstraintLayout constraintLayout;
    /**
     * ProgressBar variable.
     */
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    /**
     * Presenter - GithubPresenter.
     */
    private GithubUsersPresenter presenter;
    /**
     * BroadcastReceiver to check for internet access.
     */
    private BroadcastReceiver broadcastReceiver;
    /**
     * Snackbar variable.
     */
    private Snackbar snackbar;
    /**
     * Bundle variable.
     */
    private Bundle savedState;

    @Override
    public void displayGithubUsers(ArrayList<GithubUsers> developerList) {
        userLists = developerList;
        if (userLists != null) {
            RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager mLayoutManager =
                    new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);

            RecyclerView.Adapter adapter = new GitListAdapter(userLists);
            mRecyclerView.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ButterKnife.bind(this);

        savedState = savedInstanceState;
        presenter = new GithubUsersPresenter(this);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!checkNetworkStatus()) {
                    showSnackBar(MESSAGE);
                }
                if (userLists == null) {
                    presenter.getGitHubUsers(true);
                }
            }
        };
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        refresh();
    }

    /**
     * Refresh method.
     */
    private void refresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (!checkNetworkStatus()) {
                swipeRefreshLayout.setRefreshing(false);
                showSnackBar(MESSAGE);
            }
            presenter.getGitHubUsers(false);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, intentFilter);
        setInstanceState(savedState);
    }

    /**
     * Set instance state method.
     *
     * @param savedState - savedState method parameter
     */
    private void setInstanceState(Bundle savedState) {
        if (savedState != null) {
            userLists = savedState.getParcelableArrayList(DEVELOPER_LIST);
            displayGithubUsers(userLists);
        } else {
            if (checkNetworkStatus()) {
                presenter.getGitHubUsers(true);
            } else {
                showSnackBar("No Network connection!");
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * displayNetworkError method.
     *
     * @return NetworkUtility
     */
    private boolean checkNetworkStatus() {
        return NetworkUtility.isConnected(this);
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

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSnackBar(String message) {
        String networkMessage = MESSAGE;
        String actionText = "RETRY";
        if (!networkMessage.equalsIgnoreCase(message)) {
            networkMessage = "Bad network connection";
        }
        if (userLists != null) {
            actionText = "CLOSE";
        }
        snackbar = Snackbar.make(constraintLayout, networkMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionText, view -> {
                    if (userLists != null) {
                        hideSnackBack();
                    }
                    presenter.getGitHubUsers(true);
                    hideProgressBar();
                });
        snackbar.show();
    }

    @Override
    public void hideSnackBack() {
        if (snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void hideSwipeRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
