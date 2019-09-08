package com.omnifood.consumer.models;

import com.google.gson.annotations.SerializedName;

public class OrderDetails {

    @SerializedName("meal_id")
    private String mealId;
    @SerializedName("quantity")
    private String mealQuantity;

    public OrderDetails(String mealId, String mealQuantity) {
        this.mealId = mealId;
        this.mealQuantity = mealQuantity;
    }

    public String getMealId() {
        return mealId;
    }

    public String getMealQuantity() {
        return mealQuantity;
    }
}
