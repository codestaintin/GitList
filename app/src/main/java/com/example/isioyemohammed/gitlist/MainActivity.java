package com.example.isioyemohammed.gitlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<UserList> userLists = new ArrayList<>();

    static {
        userLists.add(new UserList("Ademola Johnson", "Andela"));
        userLists.add(new UserList("Issac John", "Southampton"));
        userLists.add(new UserList("Kobe Bryant", "Man United"));
        userLists.add(new UserList("Rajon Rondo", "Arsenal"));
    }

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
    }
}
