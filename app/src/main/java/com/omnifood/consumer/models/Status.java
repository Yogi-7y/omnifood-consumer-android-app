package com.omnifood.consumer.models;

import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String error;

    public Status(String status, String error) {
        this.status = status;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
