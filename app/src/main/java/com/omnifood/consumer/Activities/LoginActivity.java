package com.omnifood.consumer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.omnifood.consumer.OmnifoodApi;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.Login;
import com.omnifood.consumer.models.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    TextView loginUsername, loginPassword, signUpForNewAccount;
    Button loginButton;
    OmnifoodApi omnifoodApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpForNewAccount = findViewById(R.id.login_sign_up);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omnifoodApi = retrofit.create(OmnifoodApi.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = loginUsername.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                Login login = new Login(username, password);

                Call<Token> loginCall = omnifoodApi.loginUser(login);
                
                loginCall.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Token token = response.body();
                        Toast.makeText(LoginActivity.this, "You are successfully logged in..", Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(loginIntent);
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signUpForNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpForIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(signUpForIntent);
            }
        });

    }
}
