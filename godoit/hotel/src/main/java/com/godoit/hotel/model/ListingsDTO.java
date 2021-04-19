package com.godoit.hotel.model;

import lombok.Data;

import java.util.List;

@Data
public class ListingsDTO {
    private List<RoomDTO> listings;

    public ListingsDTO(List<RoomDTO> listings) {
        this.listings = listings;
    }
}
