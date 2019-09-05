package com.omnifood.consumer.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.omnifood.consumer.Adapters.RestaurantAdapter;
import com.omnifood.consumer.Adapters.SliderAdapter;
import com.omnifood.consumer.OmnifoodApi;
import com.omnifood.consumer.R;
import com.omnifood.consumer.models.Restaurant;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    List<Restaurant> restaurants;
    RecyclerView recyclerView;
    ArrayList<Restaurant> restaurantArrayList;
    RestaurantAdapter restaurantAdapter;
    OmnifoodApi omnifoodApi;
    String restaurantId;

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

    public ArrayList<Restaurant> listRestaurants() {

        recyclerView = findViewById(R.id.home_restaurant_recycler_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omnifoodApi = retrofit.create(OmnifoodApi.class);

        Call<List<Restaurant>> listCall = omnifoodApi.listRestaurants();

        listCall.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HomeActivity.this, "code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                restaurants = response.body();
                restaurantArrayList = new ArrayList<>();

                for (Restaurant restaurant : restaurants) {
                    Log.d(TAG, "onResponse: HomeActivity" + restaurant.getRestaurantId());
                    restaurantArrayList.add(new Restaurant(restaurant.getRestaurantId(), restaurant.getRestaurantName(), restaurant.getRestaurantPhone(), restaurant.getRestaurantAddress(), restaurant.getRestaurantThumbnail()));
                }
                restaurantAdapter = new RestaurantAdapter(getApplicationContext(), restaurantArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                recyclerView.setAdapter(restaurantAdapter);
            }


            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return restaurantArrayList;
    }

}























