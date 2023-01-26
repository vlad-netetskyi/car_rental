package com.github.vlad.netetskyi.repository;

import com.github.vlad.netetskyi.entity.Booking;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingRepository {

    final Map<Long, Booking> orders = new ConcurrentHashMap<>();

    private static BookingRepository INSTANCE = null;

    public static synchronized BookingRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookingRepository();
        }

        return INSTANCE;
    }

    public void save(Booking booking) {
        Booking withId = booking.withId(System.currentTimeMillis());
        orders.put(withId.getId(), withId);
    }

    public List<Booking> getAll() {
        return new ArrayList<>(orders.values());
    }

    public List<Booking> getAllByUserId(long userId) {
        return orders.values().stream().filter(o -> Objects.equals(o.getUserId(), userId)).collect(Collectors.toList());
    }

    public void updateStatus(long id, String status, Instant date) {
        orders.computeIfPresent(id, (orderId, booking) -> new Booking(
                id, booking.getUserId(), booking.getVehicleId(), booking.getCreatedAt(), booking.getRentStartDate(), booking.getRentFinishDate(), booking.getRentTotalPrice(), status, date)
        );
    }
}
