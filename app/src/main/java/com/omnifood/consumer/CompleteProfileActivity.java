package com.omnifood.consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class CompleteProfileActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutPhoneNumber;
    private TextInputLayout textInputLayoutAddress;
    Button completeProfileButton;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        textInputLayoutPhoneNumber = findViewById(R.id.complete_phone_number);
        textInputLayoutAddress = findViewById(R.id.complete_address);
        completeProfileButton = findViewById(R.id.complete_submit_button);

        completeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validatePhoneNUmber() && validateAddress()) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validatePhoneNUmber() {

        String phoneNumberInput = textInputLayoutPhoneNumber.getEditText().getText().toString().trim();

        if (phoneNumberInput.isEmpty()) {
            textInputLayoutPhoneNumber.setError("Field can't be empty");
            return false;
        } else {
            textInputLayoutPhoneNumber.setError(null);
            return true;
        }
    }

    private boolean validateAddress() {

        String addressInput = textInputLayoutAddress.getEditText().getText().toString().trim();

        if (addressInput.isEmpty()) {
            textInputLayoutAddress.setError("Field can't be empty");
            return false;
        } else {
            textInputLayoutAddress.setError(null);
            return true;
        }
    }
}
