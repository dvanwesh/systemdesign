package com.godoit.hotel.model;

import lombok.Data;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer zipcode;

    public HotelDTO() {
    }

    public HotelDTO(String name, String address, String city, String state, Integer zipcode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
}
