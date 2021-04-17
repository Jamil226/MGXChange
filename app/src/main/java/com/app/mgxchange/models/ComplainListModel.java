package com.app.mgxchange.models;

public class ComplainListModel {
    private int userID, complainID;
    private String productName, complainMessage, reference,
            contact, complainDate, serialNo, productDetail, complainStatus;

    public ComplainListModel() {
    }

    public ComplainListModel(int userID, int complainID, String productName,
                             String complainMessage, String reference,
                             String contact, String complainDate, String serialNo,
                             String productDetail, String complainStatus) {
        this.userID = userID;
        this.complainID = complainID;
        this.productName = productName;
        this.complainMessage = complainMessage;
        this.reference = reference;
        this.contact = contact;
        this.complainDate = complainDate;
        this.serialNo = serialNo;
        this.productDetail = productDetail;
        this.complainStatus = complainStatus;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setComplainID(int complainID) {
        this.complainID = complainID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setComplainMessage(String complainMessage) {
        this.complainMessage = complainMessage;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setComplainDate(String complainDate) {
        this.complainDate = complainDate;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }

    public int getUserID() {
        return userID;
    }

    public int getComplainID() {
        return complainID;
    }

    public String getProductName() {
        return productName;
    }

    public String getComplainMessage() {
        return complainMessage;
    }

    public String getReference() {
        return reference;
    }

    public String getContact() {
        return contact;
    }

    public String getComplainDate() {
        return complainDate;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public String getComplainStatus() {
        return complainStatus;
    }
}
