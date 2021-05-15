package com.app.mgxchange.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActiveSellProductListResponse {

    @SerializedName("products")
    List<Products> productsList;
    @SerializedName("status")
    String statusCode;

    public ActiveSellProductListResponse(List<Products> productsList, String statusCode) {
        this.productsList = productsList;
        this.statusCode = statusCode;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
