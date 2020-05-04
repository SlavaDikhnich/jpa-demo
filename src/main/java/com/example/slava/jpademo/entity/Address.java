package com.example.slava.jpademo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String house;
    private String city;

    public Address() {

    }

    public Address(String street, String house, String city) {
        this.street = street;
        this.house = house;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
