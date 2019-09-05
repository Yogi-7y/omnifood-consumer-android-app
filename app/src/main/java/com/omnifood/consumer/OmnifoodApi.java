package com.omnifood.consumer;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.omnifood.consumer.models.Consumer;
import com.omnifood.consumer.models.Login;
import com.omnifood.consumer.models.Meal;
import com.omnifood.consumer.models.Restaurant;
import com.omnifood.consumer.models.Token;
import com.omnifood.consumer.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OmnifoodApi {

    @POST("accounts/profile/")
    Call<User> registerUser(@Body User user);

    @POST("accounts/consumer/")
    Call<Consumer> completeProfile(@Body Consumer consumer);

    @POST("accounts/api/login")
    Call<Token> loginUser(@Body Login login);

    @GET("restaurant/restaurants/")
    Call<List<Restaurant>> listRestaurants();

    @GET("restaurant/meals/{id}")
    Call<List<Meal>> listMeals(@Path("id") int id);

}
