package com.omnifood.consumer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.omnifood.consumer.OmnifoodApi;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.User;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    public static final String BASE_URL = "http://192.168.0.4:8000";

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");

    private TextInputLayout textInputLayoutUsername, textInputLayoutFirstName,
            textInputLayoutLastName, textInputLayoutEmail, textInputLayoutPassword,
            textInputLayoutConfirmPassword;

    Button registerSignUpButton;
    OmnifoodApi omnifoodApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputLayoutUsername = findViewById(R.id.register_username);
        textInputLayoutFirstName = findViewById(R.id.register_first_name);
        textInputLayoutLastName = findViewById(R.id.register_last_name);
        textInputLayoutEmail = findViewById(R.id.register_email);
        textInputLayoutPassword = findViewById(R.id.register_password);
        textInputLayoutConfirmPassword = findViewById(R.id.register_confirm_password);
        registerSignUpButton = findViewById(R.id.register_sign_up_button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omnifoodApi = retrofit.create(OmnifoodApi.class);


        registerSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail() & validateFirstName() & validateLastName() & validatePassword() & validateUsername() & validateConfirmPassword()) {

                    String username = textInputLayoutUsername.getEditText().getText().toString().trim();
                    String firstName = textInputLayoutFirstName.getEditText().getText().toString().trim();
                    String lastName = textInputLayoutLastName.getEditText().getText().toString().trim();
                    String email = textInputLayoutEmail.getEditText().getText().toString().trim();
                    String password = textInputLayoutPassword.getEditText().getText().toString().trim();

                    User user = new User(username, firstName, lastName, email, password);

                    Call<User> userCall = omnifoodApi.registerUser(user);

                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(!response.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Code: " + response.code(), Toast.LENGTH_LONG).show();
                                return;
                            }
                            User registeredUser = response.body();
                            Toast.makeText(RegisterActivity.this, registeredUser.getFirstName() + ", Your account was successfully created.", Toast.LENGTH_SHORT).show();

                            Intent registerIntent = new Intent(getApplicationContext(), CompleteProfileActivity.class);
                            registerIntent.putExtra("registeredUser", registeredUser);
                            startActivity(registerIntent);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Error Message: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });


    }

    private boolean validateEmail() {

        String emailInput = textInputLayoutEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputLayoutEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputLayoutEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputLayoutEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputLayoutPassword.getEditText().getText().toString().trim();
        String confirmPasswordInput = textInputLayoutConfirmPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputLayoutPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputLayoutPassword.setError("Password too weak");
            return false;
        } else {
            textInputLayoutPassword.setError(null);
            return true;
        }

    }

    private boolean validateUsername() {

        String usernameInput = textInputLayoutUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputLayoutUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputLayoutUsername.setError("Username too long");
            return false;
        } else {
            textInputLayoutUsername.setError(null);
            return true;
        }
    }

    private boolean validateFirstName() {

        String firstNameInput = textInputLayoutFirstName.getEditText().getText().toString().trim();

        if (firstNameInput.isEmpty()) {
            textInputLayoutFirstName.setError("Field can't be empty");
            return false;
        } else {
            textInputLayoutFirstName.setError(null);
            return true;
        }
    }

    private boolean validateLastName() {

        String lastNameInput = textInputLayoutLastName.getEditText().getText().toString().trim();

        if (lastNameInput.isEmpty()) {
            textInputLayoutLastName.setError("Field can't be empty");
            return false;
        } else {
            textInputLayoutLastName.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String passwordInput = textInputLayoutPassword.getEditText().getText().toString().trim();
        String confirmPasswordInput = textInputLayoutConfirmPassword.getEditText().getText().toString().trim();

        if (confirmPasswordInput.isEmpty()) {
            textInputLayoutConfirmPassword.setError("Field can't be empty");
            return false;
        } else if (!passwordInput.equals(confirmPasswordInput)) {
            textInputLayoutPassword.setError("Passwords do not match");
            return false;
        } else {
            textInputLayoutConfirmPassword.setError(null);
            return true;
        }
    }


}























