package com.example.isioyemohammed.gitlist.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.isioyemohammed.gitlist.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    AutoCompleteTextView mEmailView;
    /**
     * Edit text view.
     */
    EditText mPasswordView;
    /**
     * Progress view.
     */
    @BindView(R.id.login_progress)
    View mProgressView;
    /**
     * Login form view.
     */
    @BindView(R.id.login_form)
    View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, UserActivity.class)));

        ButterKnife.bind(this);
    }
}

