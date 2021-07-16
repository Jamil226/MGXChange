package com.app.trendize.models;

public class ProductComplainModel {
    String userID;
    String serialNo, complainDate, contact, productDetails, productType, message;

    public ProductComplainModel() {

    }

    public ProductComplainModel(String userID, String serialNo, String complainDate, String contact, String productDetails, String productType, String message) {
        this.userID = userID;
        this.serialNo = serialNo;
        this.complainDate = complainDate;
        this.contact = contact;
        this.productDetails = productDetails;
        this.productType = productType;
        this.message = message;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setComplainDate(String complainDate) {
        this.complainDate = complainDate;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getComplainDate() {
        return complainDate;
    }

    public String getContact() {
        return contact;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public String getProductType() {
        return productType;
    }

    public String getMessage() {
        return message;
    }
}
