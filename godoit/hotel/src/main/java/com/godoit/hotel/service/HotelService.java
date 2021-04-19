package com.godoit.hotel.service;

import com.godoit.hotel.model.Hotel;
import com.godoit.hotel.model.HotelDTO;
import com.godoit.hotel.model.Room;
import com.godoit.hotel.model.RoomDTO;
import com.godoit.hotel.repo.HotelRepository;
import com.godoit.hotel.repo.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;

    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public Long createHotel(HotelDTO hotelDTO){
        Hotel hotel = new Hotel(hotelDTO.getName(), hotelDTO.getAddress(), hotelDTO.getCity().toLowerCase(),
            hotelDTO.getState(), hotelDTO.getZipcode());
        hotelRepository.save(hotel);
        return hotel.getId();
    }

    public Long addRoom(RoomDTO roomDTO) throws NoSuchFieldException {
        Optional<Hotel> hotel = hotelRepository.findById(roomDTO.getHotelId());
        if(!hotel.isPresent()){
            throw new NoSuchFieldException("Invalid Hotel Name");
        }
        Room room = new Room(hotel.get(), roomDTO.getDisplayName(),
            roomDTO.getQuantity(), roomDTO.getPrice());
        roomRepository.save(room);
        return room.getId();
    }

    public List<RoomDTO> getAllRoomsByCity(String city){
        return roomRepository.getRoomsByCity(city).stream().map(Room::toDto).collect(Collectors.toList());
    }

    public List<HotelDTO> getAllHotels(){
        List<HotelDTO> hotels = new ArrayList<>();
        hotelRepository.findAll().forEach(h->hotels.add(h.tpDto()));
        return hotels;
    }
}
