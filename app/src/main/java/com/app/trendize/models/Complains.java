package com.app.trendize.models;

public class Complains {
    String complainID;
    String complainReference;
    String userID;
    String userContact;
    String complainDate;
    String productType;
    String serialNumber;
    String productDetails;
    String complainMessage;
    String complainStatus;

    public Complains(String complainID, String complainReference, String userID, String userContact, String complainDate, String productType, String serialNumber, String productDetails, String complainMessage, String complainStatus) {
        this.complainID = complainID;
        this.complainReference = complainReference;
        this.userID = userID;
        this.userContact = userContact;
        this.complainDate = complainDate;
        this.productType = productType;
        this.serialNumber = serialNumber;
        this.productDetails = productDetails;
        this.complainMessage = complainMessage;
        this.complainStatus = complainStatus;
    }

    public String getComplainID() {
        return complainID;
    }

    public void setComplainID(String complainID) {
        this.complainID = complainID;
    }

    public String getComplainReference() {
        return complainReference;
    }

    public void setComplainReference(String complainReference) {
        this.complainReference = complainReference;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getComplainDate() {
        return complainDate;
    }

    public void setComplainDate(String complainDate) {
        this.complainDate = complainDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getComplainMessage() {
        return complainMessage;
    }

    public void setComplainMessage(String complainMessage) {
        this.complainMessage = complainMessage;
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }
}
