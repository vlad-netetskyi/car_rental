package com.github.vlad.netetskyi.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Booking {
    private final Long id;
    private final long userId;
    private final long vehicleId;
    private final Instant createdAt;
    private final LocalDate rentStartDate;
    private final LocalDate rentFinishDate;
    private final double rentTotalPrice;
    private final String status;
    private final Instant statusChangedAt;

    private String vehicleBrand;
    private String vehicleModel;

    private String userFullName;
    private String userPhone;

    public static final String PENDING = "Обробляється";
    public static final String ACCEPTED = "Підтверджено";
    public static final String REJECTED = "Відхилено";

    public Booking(Long id, long userId, long vehicleId, Instant createdAt, LocalDate rentStartDate, LocalDate rentFinishDate, double rentTotalPrice, String status, Instant statusChangedAt) {
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

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public LocalDate getRentFinishDate() {
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

    public String getCreatedAtStr() {
        return friendlyDate(createdAt, "dd.MM.yyyy HH:mm:ss");
    }

    public String getRentDurationStr() {
        final String pattern = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return rentStartDate.format(formatter) + " - " + rentFinishDate.format(formatter);
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public boolean canBeAccepted() {
        return Objects.equals(status, PENDING);
    }

    public boolean canBeRejected() {
        return Objects.equals(status, PENDING);
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserPhone() {
        return userPhone;
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

    public static String friendlyDate(Instant instant, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
