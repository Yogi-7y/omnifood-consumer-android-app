package com.omnifood.consumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.omnifood.consumer.Adapters.RestaurantAdapter;
import com.omnifood.consumer.models.Restaurant;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SliderView sliderView = findViewById(R.id.slider_view);
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();


        listRestaurants();
    }

    public List<Restaurant> listRestaurants() {
        restaurants = new ArrayList<>();

        restaurants.add(new Restaurant("oven story pizza", "98754785698", "London", R.drawable.three));
        restaurants.add(new Restaurant("firangi bake", "8896574896", "Canada", R.drawable.three));
        restaurants.add(new Restaurant("oven story pizza", "98754785698", "London", R.drawable.three));
        restaurants.add(new Restaurant("firangi bake", "8896574896", "Canada", R.drawable.three));
        restaurants.add(new Restaurant("oven story pizza", "98754785698", "London", R.drawable.three));
        restaurants.add(new Restaurant("firangi bake", "8896574896", "Canada", R.drawable.three));

        RecyclerView recyclerView = findViewById(R.id.home_restaurant_recycler_view);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this, restaurants);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(restaurantAdapter);

        return restaurants;
    }

}























