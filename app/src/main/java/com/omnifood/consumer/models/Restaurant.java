package com.omnifood.consumer.models;

public class Restaurant {

    private String restaurantName;
    private String restaurantPhone;
    private String restaurantAddress;
    private int restaurantThumbnail;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, String restaurantPhone, String restaurantAddress, int restaurantThumbnail) {
        this.restaurantName = restaurantName;
        this.restaurantPhone = restaurantPhone;
        this.restaurantAddress = restaurantAddress;
        this.restaurantThumbnail = restaurantThumbnail;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public int getRestaurantThumbnail() {
        return restaurantThumbnail;
    }

    public void setRestaurantThumbnail(int restaurantThumbnail) {
        this.restaurantThumbnail = restaurantThumbnail;
    }
}
