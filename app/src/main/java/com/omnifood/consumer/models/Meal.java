package com.omnifood.consumer.models;

public class Meal {

    private String mealName;
    private String mealPrice;
    private String mealDescription;
    private int mealThumbnail;

    public Meal() {
    }

    public Meal(String mealName, String mealPrice, String mealDescription, int mealThumbnail) {
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

    public int getMealThumbnail() {
        return mealThumbnail;
    }

    public void setMealThumbnail(int mealThumbnail) {
        this.mealThumbnail = mealThumbnail;
    }
}
