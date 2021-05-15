package com.app.mgxchange.models;

public class Products {

    String productID;
    String userID;
    String productReference;
    String productPurpose;
    String productType;
    String productPrice;
    String serialNumber;
    String productYear;
    String productDetails;
    String contactNumber;
    String imageOne;
    String imageTwo;
    String imageThree;
    String imageFour;
    String imageFive;
    String productCondition;
    String uploadedOn;
    String productStatus;

    public Products(String productID, String userID, String productReference, String productPurpose, String productType, String productPrice, String serialNumber, String productYear, String productDetails, String contactNumber, String imageOne, String imageTwo, String imageThree, String imageFour, String imageFive, String productCondition, String uploadedOn, String productStatus) {
        this.productID = productID;
        this.userID = userID;
        this.productReference = productReference;
        this.productPurpose = productPurpose;
        this.productType = productType;
        this.productPrice = productPrice;
        this.serialNumber = serialNumber;
        this.productYear = productYear;
        this.productDetails = productDetails;
        this.contactNumber = contactNumber;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;
        this.imageFour = imageFour;
        this.imageFive = imageFive;
        this.productCondition = productCondition;
        this.uploadedOn = uploadedOn;
        this.productStatus = productStatus;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public String getProductPurpose() {
        return productPurpose;
    }

    public void setProductPurpose(String productPurpose) {
        this.productPurpose = productPurpose;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductYear() {
        return productYear;
    }

    public void setProductYear(String productYear) {
        this.productYear = productYear;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getImageThree() {
        return imageThree;
    }

    public void setImageThree(String imageThree) {
        this.imageThree = imageThree;
    }

    public String getImageFour() {
        return imageFour;
    }

    public void setImageFour(String imageFour) {
        this.imageFour = imageFour;
    }

    public String getImageFive() {
        return imageFive;
    }

    public void setImageFive(String imageFive) {
        this.imageFive = imageFive;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(String uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}