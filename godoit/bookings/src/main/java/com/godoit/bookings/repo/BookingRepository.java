package com.godoit.bookings.repo;

import com.godoit.bookings.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    @Query(value = "SELECT * FROM Booking WHERE roomId = :roomId and " +
        "status = 'COMPLETED' and ((startDate <= :newStartDate and endDate >= :newEndDate) or " +
        "(startDate >= :newStartDate && startDate <= :newEndDate) or (endDate >= :newStartDate && endDate <= :newEndDate))", nativeQuery = true)
    List<Booking> findBookingsByDate(@Param("roomId") Long roomId,@Param("newStartDate") Instant newStartDate,@Param("newEndDate") Instant newEndDate);
}
