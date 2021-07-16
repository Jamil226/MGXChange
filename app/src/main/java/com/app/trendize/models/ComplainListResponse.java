package com.app.trendize.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComplainListResponse {
    @SerializedName("complains")
    List<Complains> complainsList;
    @SerializedName("status")
    String statusCode;

    public ComplainListResponse(List<Complains> complainsList, String statusCode) {
        this.complainsList = complainsList;
        this.statusCode = statusCode;
    }

    public List<Complains> getComplainsList() {
        return complainsList;
    }

    public void setComplainsList(List<Complains> complainsList) {
        this.complainsList = complainsList;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
