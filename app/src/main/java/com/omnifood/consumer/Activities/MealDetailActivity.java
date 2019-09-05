package com.omnifood.consumer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.omnifood.consumer.OmnifoodApi;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.Meal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealDetailActivity extends AppCompatActivity {
    private static final String TAG = "MealDetailActivity";

    int total, price;
    Meal meal;
    String result;
    ImageView mealDetailImageView;
    TextView mealNameTextView, mealPriceTextView, mealDescriptionTextView;
    Button mealPlaceOrderButton;
    ElegantNumberButton numberButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        Intent intent = getIntent();
        meal = (Meal) intent.getSerializableExtra("mealDetail");

        mealDetailImageView = findViewById(R.id.meal_detail_image_view);
        mealNameTextView = findViewById(R.id.meal_detail_meal_name);
        mealPriceTextView = findViewById(R.id.meal_detail_price);
        mealDescriptionTextView = findViewById(R.id.meal_detail_description);
        numberButton = findViewById(R.id.meal_detail_number_button);
        mealPlaceOrderButton = findViewById(R.id.meal_place_order_button);

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(meal.getMealThumbnail())
                .into(mealDetailImageView);

        mealNameTextView.setText(meal.getMealName());
        mealPriceTextView.setText(meal.getMealPrice());
        mealDescriptionTextView.setText(meal.getMealDescription());
        price = Integer.parseInt(mealPriceTextView.getText().toString());
        numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MealDetailActivity.this, "Clicked " + numberButton.getNumber(), Toast.LENGTH_SHORT).show();
                int number = Integer.parseInt(numberButton.getNumber());
                total = price * number;
                Log.d(TAG, "Price: " + total);
                result = "" + total + " = " + number + " x " + price;
                mealPriceTextView.setText(result);
            }
        });


    }
}
