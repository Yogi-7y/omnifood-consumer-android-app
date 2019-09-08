package com.omnifood.consumer.models;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("token")
    private String userToken;
    @SerializedName("address")
    private String userAddress;
    @SerializedName("meal_id")
    private String mealId;
    @SerializedName("restaurant_id")
    private String restaurantId;
    @SerializedName("total")
    private String mealTotal;
    @SerializedName("quantity")
    private String mealQuantity;

    public Order(String userToken, String userAddress, String mealId, String restaurantId, String mealTotal, String mealQuantity) {
        this.userToken = userToken;
        this.userAddress = userAddress;
        this.mealId = mealId;
        this.restaurantId = restaurantId;
        this.mealTotal = mealTotal;
        this.mealQuantity = mealQuantity;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getMealId() {
        return mealId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getMealTotal() {
        return mealTotal;
    }

    public String getMealQuantity() {
        return mealQuantity;
    }
}
