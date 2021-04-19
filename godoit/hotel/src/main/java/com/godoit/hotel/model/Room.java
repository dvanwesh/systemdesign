package com.godoit.hotel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Data
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String displayName;

    private boolean active;

    private int quantity;

    private int price;

    public Room(Hotel hotel, String displayName, int quantity, int price) {
        this.hotel = hotel;
        this.displayName = displayName;
        this.quantity = quantity;
        this.price = price;
        this.active = true;
    }

    public Room() {}

    public RoomDTO toDto(){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(this.id);
        roomDTO.setHotelId(this.getHotel().getId());
        roomDTO.setDisplayName(this.displayName);
        roomDTO.setQuantity(this.quantity);
        roomDTO.setPrice(this.price);
        return roomDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return active == room.active && quantity == room.quantity && price == room.price && Objects.equals(id, room.id) && Objects.equals(hotel, room.hotel) && Objects.equals(displayName, room.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotel, displayName, active, quantity, price);
    }
}
