package com.example.isioyemohammed.gitlist.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.isioyemohammed.gitlist.GitListAdapter;
import com.example.isioyemohammed.gitlist.presenter.GithubUsersPresenter;
import com.example.isioyemohammed.gitlist.R;
import com.example.isioyemohammed.gitlist.model.GithubUsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MainActivity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * List - List off github users.
     */
    private static List<GithubUsers> userLists = new ArrayList<>();
    /**
     * Presenter - GithubPresenter.
     */
    private GithubUsersPresenter presenter = new GithubUsersPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GitListAdapter adapter = new GitListAdapter(userLists, this);
        mRecyclerView.setAdapter(adapter);

        presenter.getGitHubUsers(mRecyclerView);
    }
}
