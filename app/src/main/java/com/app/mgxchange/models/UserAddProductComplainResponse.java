package com.app.mgxchange.models;

import com.google.gson.annotations.SerializedName;

public class UserAddProductComplainResponse {

    @SerializedName("status")
    String complainStatus;
    @SerializedName("message")
    String complainMessage;

    public UserAddProductComplainResponse(String complainStatus, String complainMessage) {
        this.complainStatus = complainStatus;
        this.complainMessage = complainMessage;
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }

    public String getComplainMessage() {
        return complainMessage;
    }

    public void setComplainMessage(String complainMessage) {
        this.complainMessage = complainMessage;
    }
}
