package com.app.trendize.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userid")
    String userID;
    @SerializedName("firstname")
    String firstName;
    @SerializedName("lastname")
    String lastName;
    @SerializedName("createdusing")
    String LoginMethod;
    String email, contact, address;
    @SerializedName("imagepath")
    String imagePath;

    public User(String userID, String firstName, String lastName, String loginMethod, String email, String contact, String address, String imagePath) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        LoginMethod = loginMethod;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.imagePath = imagePath;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginMethod() {
        return LoginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        LoginMethod = loginMethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
