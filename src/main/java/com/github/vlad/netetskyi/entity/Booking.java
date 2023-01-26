package com.github.vlad.netetskyi.entity;

import java.time.Instant;
import java.util.Objects;

public class Booking {
    private final Long id;
    private final long userId;
    private final long vehicleId;
    private final Instant createdAt;
    private final Instant rentStartDate;
    private final Instant rentFinishDate;
    private final double rentTotalPrice;
    private final String status;
    private final Instant statusChangedAt;

    public Booking(Long id, long userId, long vehicleId, Instant createdAt, Instant rentStartDate, Instant rentFinishDate, double rentTotalPrice, String status, Instant statusChangedAt) {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.createdAt = createdAt;
        this.rentStartDate = rentStartDate;
        this.rentFinishDate = rentFinishDate;
        this.rentTotalPrice = rentTotalPrice;
        this.status = status;
        this.statusChangedAt = statusChangedAt;
    }

    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getRentStartDate() {
        return rentStartDate;
    }

    public Instant getRentFinishDate() {
        return rentFinishDate;
    }

    public double getRentTotalPrice() {
        return rentTotalPrice;
    }

    public String getStatus() {
        return status;
    }

    public Instant getStatusChangedAt() {
        return statusChangedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return userId == booking.userId && vehicleId == booking.vehicleId && Double.compare(booking.rentTotalPrice, rentTotalPrice) == 0 && Objects.equals(id, booking.id) && Objects.equals(createdAt, booking.createdAt) && Objects.equals(rentStartDate, booking.rentStartDate) && Objects.equals(rentFinishDate, booking.rentFinishDate) && Objects.equals(status, booking.status) && Objects.equals(statusChangedAt, booking.statusChangedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, vehicleId, createdAt, rentStartDate, rentFinishDate, rentTotalPrice, status, statusChangedAt);
    }

    @Override
    public String toString() {
        return "RentOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", vehicleId=" + vehicleId +
                ", createdAt=" + createdAt +
                ", rentStartDate=" + rentStartDate +
                ", rentFinishDate=" + rentFinishDate +
                ", rentTotalPrice=" + rentTotalPrice +
                ", status='" + status + '\'' +
                ", statusChangedAt=" + statusChangedAt +
                '}';
    }

    public Booking withId(Long id) {
        return new Booking(id, userId, vehicleId, createdAt, rentStartDate, rentFinishDate, rentTotalPrice, status, statusChangedAt);
    }
}
