package com.godoit.hotel.repo;

import com.godoit.hotel.model.Hotel;
import com.godoit.hotel.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> getRoomsByHotel(@Param("hotel") Hotel hotel);

    @Query(value = "SELECT rm.* FROM Room rm, hotel h WHERE h.id = rm.hotel_id and h.city = :city", nativeQuery = true)
    List<Room> getRoomsByCity(@Param("city") String city);
}
