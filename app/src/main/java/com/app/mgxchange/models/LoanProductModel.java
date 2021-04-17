package com.app.mgxchange.models;

public class LoanProductModel {
    String productAmount, productYear, productContact, productDetails,
            productType, productCondition;

    public LoanProductModel() {
    }

    public LoanProductModel(String productAmount, String productYear, String productContact, String productDetails, String productType, String productCondition) {
        this.productAmount = productAmount;
        this.productYear = productYear;
        this.productContact = productContact;
        this.productDetails = productDetails;
        this.productType = productType;
        this.productCondition = productCondition;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public void setProductYear(String productYear) {
        this.productYear = productYear;
    }

    public void setProductContact(String productContact) {
        this.productContact = productContact;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public String getProductYear() {
        return productYear;
    }

    public String getProductContact() {
        return productContact;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductCondition() {
        return productCondition;
    }
}