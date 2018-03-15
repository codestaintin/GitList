package com.example.isioyemohammed.gitlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView username;
    TextView company;
    TextView gitUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        username = findViewById(R.id.username);
        company = findViewById(R.id.company);
        gitUrl = findViewById(R.id.gitUrl);

        Intent intent = this.getIntent();

        String name = intent.getStringExtra("USERNAME");
        String organisation = intent.getStringExtra("COMPANY");
        String url = intent.getStringExtra("GITURL");

        username.setText(name);
        company.setText(organisation);
        gitUrl.setText(url);

    }
}
