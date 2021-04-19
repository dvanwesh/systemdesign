package com.godoit.hotel.model;

import lombok.Data;

@Data
public class RoomDTO {
    private Long id;
    private Long hotelId;
    private String displayName;
    private int quantity;
    private int price;

    public RoomDTO() {
    }

    public RoomDTO(Long hotelId, String displayName, int quantity, int price) {
        this.hotelId = hotelId;
        this.displayName = displayName;
        this.quantity = quantity;
        this.price = price;
    }
}
