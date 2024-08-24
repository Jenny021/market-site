package com.example.yourapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView emailErrorTextView;
    private TextView passwordErrorTextView;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        emailErrorTextView = findViewById(R.id.email_error);
        passwordErrorTextView = findViewById(R.id.password_error);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle registration redirection
                redirectToRegister();
            }
        });
    }

    private void onLogin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            emailErrorTextView.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            emailErrorTextView.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(password)) {
            passwordErrorTextView.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            passwordErrorTextView.setVisibility(View.GONE);
        }

        if (valid) {
            // Perform login
        }
    }

    private void redirectToRegister() {
        // Implement redirection to register activity
    }
}
