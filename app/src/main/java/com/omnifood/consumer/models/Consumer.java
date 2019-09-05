package com.omnifood.consumer.models;

import com.google.gson.annotations.SerializedName;

public class Consumer {


    @SerializedName("user")
    private int userId;
    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;

    public Consumer(int userId, String phone, String address) {
        this.userId = userId;
        this.phone = phone;
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
