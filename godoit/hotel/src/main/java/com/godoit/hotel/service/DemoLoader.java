package com.godoit.hotel.service;

import com.godoit.hotel.model.HotelDTO;
import com.godoit.hotel.model.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoLoader implements CommandLineRunner {
    private final HotelService hotelService;

    @Autowired
    public DemoLoader(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public void run(String... args) throws Exception {
        Long id = hotelService.createHotel(new HotelDTO("Hayt", "149 26th St NW",
            "ATLANTA", "GA", 30309));
        hotelService.addRoom(new RoomDTO(id, "Delux", 10, 100));
        hotelService.addRoom(new RoomDTO(id, "Platinum", 5, 150));
        id = hotelService.createHotel(new HotelDTO("BestWestern", "45 8th St NE",
            "AUSTIN", "TX", 20208));
        hotelService.addRoom(new RoomDTO(id, "Delux", 15, 80));
        hotelService.addRoom(new RoomDTO(id, "Platinum", 10, 120));
    }
}
