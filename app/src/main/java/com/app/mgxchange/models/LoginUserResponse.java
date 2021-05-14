package com.app.mgxchange.models;

import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {

    User user;
    @SerializedName("status")
    String userStatus;
    @SerializedName("message")
    String userMessage;

    public LoginUserResponse(User user, String userStatus, String userMessage) {
        this.user = user;
        this.userStatus = userStatus;
        this.userMessage = userMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
