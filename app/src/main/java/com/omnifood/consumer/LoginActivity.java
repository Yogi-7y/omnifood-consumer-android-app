package com.omnifood.consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView signUpForNewAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpForNewAccount = findViewById(R.id.login_sign_up);

        signUpForNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpForIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(signUpForIntent);
            }
        });

    }
}
