package com.omnifood.consumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.omnifood.consumer.Adapters.MealListAdapter;
import com.omnifood.consumer.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMealActivity extends AppCompatActivity {

    private static final String TAG = "RestaurantMealActivity";

    ArrayList<Meal> meals;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_meal);

        meals = new ArrayList<>();

        meals.add(new Meal("Butter Chicken", "₹350", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.three));
        meals.add(new Meal("Butter Chicken", "₹350", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.three));
        meals.add(new Meal("Butter Chicken", "₹350", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", R.drawable.three));

        recyclerView = findViewById(R.id.restaurant_meal_recycler_view);
        MealListAdapter mealListAdapter = new MealListAdapter(getApplicationContext(), meals);
        recyclerView.setAdapter(mealListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
