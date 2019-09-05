package com.omnifood.consumer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.omnifood.consumer.Adapters.MealListAdapter;
import com.omnifood.consumer.OmnifoodApi;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.Meal;
import com.omnifood.consumer.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantMealActivity extends AppCompatActivity {
    private static final String TAG = "RestaurantMealActivity";

    ArrayList<Meal> mealArrayList;
    RecyclerView recyclerView;
    Restaurant restaurant;
    OmnifoodApi omnifoodApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_meal);

        Intent intent = getIntent();
        restaurant = (Restaurant) intent.getSerializableExtra("restaurant");


        recyclerView = findViewById(R.id.restaurant_meal_recycler_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omnifoodApi = retrofit.create(OmnifoodApi.class);

        Log.d(TAG, "onCreate: Id" + Integer.parseInt(restaurant.getRestaurantId()));
        Call<List<Meal>> listCall = omnifoodApi.listMeals(Integer.parseInt(restaurant.getRestaurantId()));

        listCall.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RestaurantMealActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Meal> meals = response.body();
                mealArrayList = new ArrayList<>();

                for (Meal meal : meals) {
                    mealArrayList.add(new Meal(meal.getMealId(), meal.getMealName(), meal.getMealPrice(), meal.getMealDescription(), meal.getMealThumbnail()));
                }
                MealListAdapter mealListAdapter = new MealListAdapter(getApplicationContext(), mealArrayList);
                recyclerView.setAdapter(mealListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                Toast.makeText(RestaurantMealActivity.this, "Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
