package com.omnifood.consumer.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meal implements Serializable {

    @SerializedName("id")
    private String mealId;
    @SerializedName("meal")
    private String mealName;
    @SerializedName("price")
    private String mealPrice;
    @SerializedName("description")
    private String mealDescription;
    @SerializedName("image")
    private String mealThumbnail;

    public String getMealId() {
        return mealId;
    }

    public Meal() {
    }

    public Meal(String mealName, String mealPrice, String mealDescription, String mealThumbnail) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.mealDescription = mealDescription;
        this.mealThumbnail = mealThumbnail;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public String getMealThumbnail() {
        return mealThumbnail;
    }

    public Meal(String mealId, String mealName, String mealPrice, String mealDescription, String mealThumbnail) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.mealDescription = mealDescription;
        this.mealThumbnail = mealThumbnail;
    }

    public void setMealThumbnail(String mealThumbnail) {
        this.mealThumbnail = mealThumbnail;
    }
}
