package com.godoit.bookings.model;

import lombok.Data;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Data
public class AvailableRoom {
    @Id
    private long roomId;
    private ZonedDateTime date;
    private int initialQuantity;
    private int availableQuantity;

    public AvailableRoom(long roomId, ZonedDateTime date, int initialQuantity, int availableQuantity) {
        this.roomId = roomId;
        this.date = date;
        this.initialQuantity = initialQuantity;
        this.availableQuantity = availableQuantity;
    }

    public AvailableRoom() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableRoom that = (AvailableRoom) o;
        return roomId == that.roomId && initialQuantity == that.initialQuantity && availableQuantity == that.availableQuantity && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, date, initialQuantity, availableQuantity);
    }
}
