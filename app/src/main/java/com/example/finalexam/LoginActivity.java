package com.example.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText etUserName;
    private TextInputEditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.login));
        }

        initViews();
        btnLogin.setOnClickListener(view -> authenticateUser());
    }

    private void initViews() {
        etUserName = findViewById(R.id.et_user_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void authenticateUser() {
        if (TextUtils.isEmpty(etUserName.getText())) {
            Toast.makeText(this, getString(R.string.username_empty_error), Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText())) {
            Toast.makeText(this, getString(R.string.password_empty_error), Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (Objects.requireNonNull(etUserName.getText()).toString().trim()
                .equalsIgnoreCase(getString(R.string.username)) &&
                Objects.requireNonNull(etPassword.getText()).toString().trim()
                        .equalsIgnoreCase(getString(R.string.password))) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, getString(R.string.invalid_username_password),
                    Toast.LENGTH_SHORT).show();
        }
    }
}