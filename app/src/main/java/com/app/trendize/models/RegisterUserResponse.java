package com.app.trendize.models;

import com.google.gson.annotations.SerializedName;

public class RegisterUserResponse {

    @SerializedName("status")
    String userStatus;
    @SerializedName("message")
    String userMessage;

    public RegisterUserResponse(String userStatus, String userMessage) {
        this.userStatus = userStatus;
        this.userMessage = userMessage;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
