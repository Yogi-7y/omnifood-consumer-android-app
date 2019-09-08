package com.omnifood.consumer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.gson.Gson;
import com.omnifood.consumer.OmnifoodApi;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.Consumer;
import com.omnifood.consumer.models.Meal;
import com.omnifood.consumer.models.Order;
import com.omnifood.consumer.models.OrderDetails;
import com.omnifood.consumer.models.Restaurant;
import com.omnifood.consumer.models.Status;
import com.omnifood.consumer.models.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealDetailActivity extends AppCompatActivity {
    private static final String TAG = "MealDetailActivity";

    int total, price;
    Meal meal;
    String result;
    Token token;
    Gson gson;
    Restaurant restaurant;
    Consumer consumer;
    ImageView mealDetailImageView;
    TextView mealNameTextView, mealPriceTextView, mealDescriptionTextView;
    Button mealPlaceOrderButton;
    ElegantNumberButton numberButton;
    androidx.appcompat.widget.Toolbar mealNameToolbar;
    OmnifoodApi omnifoodApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        Intent intent = getIntent();
        meal = (Meal) intent.getSerializableExtra("mealDetail");
//        Intent tokenIntent = getIntent();
//        String token = tokenIntent.getStringExtra("token");

        SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("token", "");
        token = gson.fromJson(json, Token.class);
        Log.d(TAG, "onCreate: Token: " + token);

        gson = new Gson();
        SharedPreferences restaurantIdPreference = getSharedPreferences("restaurant", MODE_PRIVATE);
        String json_ = restaurantIdPreference.getString("restaurant", "");
        restaurant = gson.fromJson(json_, Restaurant.class);
        Log.d(TAG, "onCreate: RestaurantId: " + restaurant.getRestaurantPhone());

//        SharedPreferences consumerSharedPreferences = getSharedPreferences("consumer", MODE_PRIVATE);
//        Gson consumerGson = new Gson();
//        String consumerJson = consumerSharedPreferences.getString("consumer", "");
//        consumer = consumerGson.fromJson(consumerJson, Consumer.class);
//        Log.d(TAG, "onCreate: Consumer: " + consumer.getAddress());

        mealDetailImageView = findViewById(R.id.meal_detail_image_view);
        mealNameTextView = findViewById(R.id.meal_detail_meal_name);
        mealPriceTextView = findViewById(R.id.meal_detail_price);
        mealDescriptionTextView = findViewById(R.id.meal_detail_description);
        numberButton = findViewById(R.id.meal_detail_number_button);
        mealPlaceOrderButton = findViewById(R.id.meal_place_order_button);
        mealNameToolbar = findViewById(R.id.meal_detail_toolbar);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omnifoodApi = retrofit.create(OmnifoodApi.class);

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(meal.getMealThumbnail())
                .into(mealDetailImageView);

        mealNameTextView.setText(meal.getMealName());
        mealNameToolbar.setTitle(meal.getMealName());
        mealPriceTextView.setText(meal.getMealPrice());
        mealDescriptionTextView.setText(meal.getMealDescription());
        price = Integer.parseInt(mealPriceTextView.getText().toString());
        numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MealDetailActivity.this, "Clicked " + numberButton.getNumber(), Toast.LENGTH_SHORT).show();
                int number = Integer.parseInt(numberButton.getNumber());
                total = price;
                if (number == 10) {
                    Toast.makeText(MealDetailActivity.this, "Max order limit reached", Toast.LENGTH_SHORT).show();
                }
                total = price * number;
                Log.d(TAG, "Price: " + total);
                result = "" + total + " = " + number + " x " + price;
                mealPriceTextView.setText(result);
            }
        });

        mealPlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderDetails orderDetails = new OrderDetails(meal.getMealId(), numberButton.getNumber());
                Call<Status> statusCall = omnifoodApi.placeOrder(
                        token.getToken(),
                        token.getAddress(),
                        meal.getMealId(),
                        restaurant.getRestaurantId(),
                        String.valueOf(total),
                        numberButton.getNumber()


                );

                statusCall.enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(MealDetailActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onResponse: Code: " + response.errorBody());
                            return;
                        }

                        Status status = response.body();
                        Log.d(TAG, "onResponse: Status" + status.getStatus());
                        Toast.makeText(MealDetailActivity.this, "status: " + status.getStatus(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        Log.d(TAG, "onFailure: Error message: " + t.getMessage());
                        Toast.makeText(MealDetailActivity.this, "Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
