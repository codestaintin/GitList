package com.example.isioyemohammed.gitlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.isioyemohammed.gitlist.Presenter.GithubUsersPresenter;
import com.example.isioyemohammed.gitlist.model.GithubUsers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<GithubUsers> userLists = new ArrayList<>();
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
