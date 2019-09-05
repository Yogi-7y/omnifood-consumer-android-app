package com.omnifood.consumer.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    @SerializedName("id")
    private String restaurantId;
    @SerializedName("name")
    private String restaurantName;
    @SerializedName("phone")
    private String restaurantPhone;
    @SerializedName("address")
    private String restaurantAddress;
    @SerializedName("logo")
    private String restaurantThumbnail;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, String restaurantPhone, String restaurantAddress, String restaurantThumbnail) {
        this.restaurantName = restaurantName;
        this.restaurantPhone = restaurantPhone;
        this.restaurantAddress = restaurantAddress;
        this.restaurantThumbnail = restaurantThumbnail;
    }

    public Restaurant(String restaurantId, String restaurantName, String restaurantPhone, String restaurantAddress, String restaurantThumbnail) {
        this.restaurantId = restaurantId;
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

    public String getRestaurantThumbnail() {
        return restaurantThumbnail;
    }

    public void setRestaurantThumbnail(String restaurantThumbnail) {
        this.restaurantThumbnail = restaurantThumbnail;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
