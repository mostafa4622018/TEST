package com.example.jsoninandroidstudio.Model;

public class Address {
    private String country;
    private String street;
    private String alley;
    private String number;

    public Address(String country, String street, String alley, String number) {
        this.country = country;
        this.street = street;
        this.alley = alley;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
