package com.godoit.bookings.service;

import com.godoit.bookings.model.Booking;
import com.godoit.bookings.model.Status;
import com.godoit.bookings.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingServiceImpl {
    private final BookingRepository bookingRepository;
    //private Map<Long, Map<Long, Map<Instant, Long>>> hotelCache;
    private Map<Long, Map<Instant, Long>> roomCache;
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        roomCache = new ConcurrentHashMap<>();
    }

    private boolean checkIfRoomIsAvailable(Long roomId,Long userId, Instant startDate, Instant endDate){
        if(!roomCache.containsKey(roomId)){
            List<Booking> bookings = bookingRepository.findBookingsByDate(roomId, startDate,endDate);
            return bookings.isEmpty();
        }
        Map<Instant, Long> roomStatusMap = roomCache.get(roomId);
        if(roomStatusMap.containsKey(startDate)){
            return roomStatusMap.get(startDate) == userId;
        }
        if(roomStatusMap.containsKey(endDate)){
            return roomStatusMap.get(endDate) == userId;
        }
        return true;
    }

    private void addBooking(Long roomId, Long userId, Instant startDate, Instant endDate) throws NoSuchFieldException {
        if(checkIfRoomIsAvailable(roomId, userId, startDate, endDate)){
            roomCache.computeIfAbsent(roomId, a -> new ConcurrentHashMap<>());
            Map<Instant, Long> roomStatusMap = roomCache.get(roomId);
            for(Instant curr = startDate; curr.isBefore(endDate); curr = curr.plus(1, ChronoUnit.DAYS)){
                roomStatusMap.put(curr, userId);
            }
        } else{
            throw new NoSuchFieldException("Room not available");
        }
    }

    public void registerBookings(Long hotelId, Long roomId, Long userId, Instant startDate, Instant endDate){
        bookingRepository.save(new Booking(roomId, userId, startDate,
            endDate, 1, Status.COMPLETED, "invoice_"+userId+"_hotel_"+hotelId+"_room_"+roomId));
        clearRoomCache(roomId, userId, startDate, endDate);
    }

    private void clearRoomCache(Long roomId, Long userId, Instant startDate, Instant endDate) {
        if(!roomCache.containsKey(roomId)){
            return;
        }
        Map<Instant, Long> roomStatusMap = roomCache.get(roomId);
        for(Instant curr = startDate; curr.isBefore(endDate); curr = curr.plus(1, ChronoUnit.DAYS)){
            roomStatusMap.remove(curr);
        }
    }
}
