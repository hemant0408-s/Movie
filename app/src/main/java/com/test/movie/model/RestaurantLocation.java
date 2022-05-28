package com.test.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestaurantLocation {
    @SerializedName("address1")
    private String address1;
    @SerializedName("city")
    private String city;
    @SerializedName("zip_code")
    private String zipCode;
    @SerializedName("country")
    private String country;
    @SerializedName("state")
    private String state;
    @SerializedName("display_address")
    private ArrayList<String> displayAddress;

    public String getAddress1() {
        return address1;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public ArrayList<String> getDisplayAddress() {
        return displayAddress;
    }
}
