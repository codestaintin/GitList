package com.example.isioyemohammed.gitlist.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isioyemohammed.gitlist.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by isioyemohammed on 07/03/2018.
 */

public class DetailActivity extends AppCompatActivity {
    /**
     * TextView - username parameter.
     */
    @BindView(R.id.username)
    TextView username;
    /**
     * TextView - company parameter.
     */
    @BindView(R.id.company)
    TextView company;
    /**
     * TextView - gitUrl parameter.
     */
    @BindView(R.id.gitUrl)
    TextView gitUrl;
    /**
     * ImageView - image parameter.
     */
    @BindView(R.id.profileImage)
    ImageView image;
    /**
     * Toolbar widget.
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    /**
     * CollapsingToolbarLayout widget.
     */
    @BindView(R.id.collapse)
    CollapsingToolbarLayout collapsingToolbarLayout;

    /**
     * Bundle instance for saving current state.
     *
     * @param savedInstanceState - savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        FloatingActionButton shareButton = findViewById(R.id.floatActionButton);

        final String profileLink = "https://github.com/" + intent.getStringExtra("USERNAME");
        final String message = "View the profile of this super android apps developer "
                + profileLink;
        shareButton.setOnClickListener(view -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(shareIntent);
        });

        String name = intent.getStringExtra("USERNAME");
        String organisation = intent.getStringExtra("COMPANY");
        String url = intent.getStringExtra("GITURL");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(name);

        Picasso.get()
                .load(intent.getExtras().getString("IMAGE"))
                .into(image);

        username.setText(name);
        company.setText(organisation);
        gitUrl.setText(url);

    }
}
