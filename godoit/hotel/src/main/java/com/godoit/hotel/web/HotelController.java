package com.godoit.hotel.web;

import com.godoit.hotel.model.HotelDTO;
import com.godoit.hotel.model.ListingsDTO;
import com.godoit.hotel.model.RoomDTO;
import com.godoit.hotel.service.HotelService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@Log
@RequestMapping("/api")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotel")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Long> createHotel(@RequestBody @Validated HotelDTO hotelDTO){
        Long id = hotelService.createHotel(hotelDTO);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/listing")
    ResponseEntity<ListingsDTO> getListingsByCity(@RequestParam("city") String city){
        List<RoomDTO> rooms = hotelService.getAllRoomsByCity(city.toLowerCase());
        ListingsDTO response = new ListingsDTO(rooms);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/hotel")
    ResponseEntity<List<HotelDTO>> getListingsByCity(){
        List<HotelDTO> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok().body(hotels);
    }
}
