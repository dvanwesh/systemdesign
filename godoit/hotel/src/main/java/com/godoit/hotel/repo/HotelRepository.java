package com.godoit.hotel.repo;

import com.godoit.hotel.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findHotelByZipcode(@Param("zipcode") int zipCode);

    List<Hotel> getAllByCity(@Param("city") String city);
}
