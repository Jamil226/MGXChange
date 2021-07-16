package com.app.trendize.models;

import com.google.gson.annotations.SerializedName;

public class UserProfileImageResponse {

    @SerializedName("status")
    String imageStatus;
    @SerializedName("message")
    String imageMessage;

    public UserProfileImageResponse(String imageStatus, String imageMessage) {
        this.imageStatus = imageStatus;
        this.imageMessage = imageMessage;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public String getImageMessage() {
        return imageMessage;
    }

    public void setImageMessage(String imageMessage) {
        this.imageMessage = imageMessage;
    }
}
