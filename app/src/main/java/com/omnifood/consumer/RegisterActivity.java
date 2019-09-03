package com.omnifood.consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

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

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPasword;
    Button registerSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputLayoutUsername = findViewById(R.id.register_username);
        textInputLayoutFirstName = findViewById(R.id.register_first_name);
        textInputLayoutLastName = findViewById(R.id.register_last_name);
        textInputLayoutEmail = findViewById(R.id.register_email);
        textInputLayoutPassword = findViewById(R.id.register_password);
        textInputLayoutConfirmPasword = findViewById(R.id.register_confirm_password);
        registerSignUpButton = findViewById(R.id.register_sign_up_button);


        registerSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail() & validateFirstName() & validateLastName() & validatePassword() & validateUsername() & validateConfirmPassword()) {
                    Intent intent = new Intent(getApplicationContext(), CompleteProfileActivity.class);
                    startActivity(intent);
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
        String confirmPasswordInput = textInputLayoutConfirmPasword.getEditText().getText().toString().trim();

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
        String confirmPasswordInput = textInputLayoutConfirmPasword.getEditText().getText().toString().trim();

        if (confirmPasswordInput.isEmpty()) {
            textInputLayoutConfirmPasword.setError("Field can't be empty");
            return false;
        } else if (!passwordInput.equals(confirmPasswordInput)) {
            textInputLayoutPassword.setError("Passwords do not match");
            return false;
        } else {
            textInputLayoutConfirmPasword.setError(null);
            return true;
        }
    }


}























