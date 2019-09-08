package com.omnifood.consumer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.omnifood.consumer.OmnifoodApi;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.Consumer;
import com.omnifood.consumer.models.User;

import java.nio.file.ReadOnlyFileSystemException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class CompleteProfileActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutPhoneNumber, textInputLayoutAddress;
    Button completeProfileButton;
    OmnifoodApi omnifoodApi;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        final Intent getRegisteredUserIntent = getIntent();
        final User user = (User) getRegisteredUserIntent.getSerializableExtra("registeredUser");

        textInputLayoutPhoneNumber = findViewById(R.id.complete_phone_number);
        textInputLayoutAddress = findViewById(R.id.complete_address);
        completeProfileButton = findViewById(R.id.complete_submit_button);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omnifoodApi = retrofit.create(OmnifoodApi.class);


        completeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validatePhoneNUmber() && validateAddress()) {

                    String phone = textInputLayoutPhoneNumber.getEditText().getText().toString().trim();
                    String address = textInputLayoutAddress.getEditText().getText().toString().trim();

                    Consumer consumer = new Consumer(Integer.parseInt(user.getId()), phone, address);

                    Call<Consumer> consumerCall = omnifoodApi.completeProfile(consumer);

                    consumerCall.enqueue(new Callback<Consumer>() {
                        @Override
                        public void onResponse(Call<Consumer> call, Response<Consumer> response) {
                            if(!response.isSuccessful()) {
                                Toast.makeText(CompleteProfileActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Consumer consumer = response.body();
                            Toast.makeText(CompleteProfileActivity.this, "Successful...", Toast.LENGTH_SHORT).show();

                            SharedPreferences sharedPreferences = getSharedPreferences("consumer", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(consumer);
                            editor.putString("consumer", json);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Consumer> call, Throwable t) {
                            Toast.makeText(CompleteProfileActivity.this, "Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

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
