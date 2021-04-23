package com.godoit.hotel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
public class Hotel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    private String address;

    private String city;

    private String state;

    private Integer zipcode;

    private boolean active;

    public HotelDTO tpDto(){
        HotelDTO hotelDTO = new HotelDTO(name, address, city, state, zipcode);
        hotelDTO.setId(id);
        return hotelDTO;
    }

    public Hotel(String name, String address, String city, String state, Integer zipcode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.active = true;
    }

    public Hotel() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return active == hotel.active && Objects.equals(id, hotel.id) && Objects.equals(name, hotel.name) && Objects.equals(address, hotel.address) && Objects.equals(city, hotel.city) && Objects.equals(state, hotel.state) && Objects.equals(zipcode, hotel.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, city, state, zipcode, active);
    }
}
