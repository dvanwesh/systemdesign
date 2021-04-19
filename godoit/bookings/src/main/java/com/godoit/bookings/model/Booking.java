package com.godoit.bookings.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Data
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private Long userId;
    private Instant startDate;
    private Instant endDate;
    private int numOfRooms;
    private Status status;
    private String invoiceId;

    public Booking(Long roomId, Long userId, Instant startDate, Instant endDate,
                   int numOfRooms, Status status, String invoiceId) {
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numOfRooms = numOfRooms;
        this.status = status;
        this.invoiceId = invoiceId;
    }

    public Booking() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return numOfRooms == booking.numOfRooms && Objects.equals(id, booking.id) && Objects.equals(roomId, booking.roomId) && Objects.equals(userId, booking.userId) && Objects.equals(startDate, booking.startDate) && Objects.equals(endDate, booking.endDate) && status == booking.status && Objects.equals(invoiceId, booking.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomId, userId, startDate, endDate, numOfRooms, status, invoiceId);
    }
}
