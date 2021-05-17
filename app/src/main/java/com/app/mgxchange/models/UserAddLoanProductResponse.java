package com.app.mgxchange.models;

import com.google.gson.annotations.SerializedName;

public class UserAddLoanProductResponse {

    @SerializedName("status")
    String userStatus;
    @SerializedName("message")
    String productMessage;

    public UserAddLoanProductResponse(String userStatus, String productMessage) {
        this.userStatus = userStatus;
        this.productMessage = productMessage;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getProductMessage() {
        return productMessage;
    }

    public void setProductMessage(String productMessage) {
        this.productMessage = productMessage;
    }
}
