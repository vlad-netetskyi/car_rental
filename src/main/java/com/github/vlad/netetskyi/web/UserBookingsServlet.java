package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Booking;
import com.github.vlad.netetskyi.entity.Vehicle;
import com.github.vlad.netetskyi.repository.BookingRepository;
import com.github.vlad.netetskyi.repository.VehicleRepository;
import com.github.vlad.netetskyi.service.security.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UserBookings", urlPatterns = {"/bookings"})
public class UserBookingsServlet extends HttpServlet {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;

    public UserBookingsServlet() {
        this.bookingRepository = BookingRepository.getInstance();
        this.vehicleRepository = VehicleRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ((User) req.getSession().getAttribute("user"));
        if (user != null) {
            List<Booking> bookings;
            if (user.isAdmin()) {
                bookings = bookingRepository.getAll();
            } else {
                bookings = bookingRepository.getAllByUserId(user.getId());
            }

            List<Long> vehicleIds = bookings.stream().map(Booking::getVehicleId).toList();
            List<Vehicle> vehicles = vehicleRepository.getByIds(vehicleIds);
            for (Booking booking : bookings) {
                long vehicleId = booking.getVehicleId();
                vehicles.stream().filter(v -> Objects.equals(v.getId(), vehicleId)).findAny().ifPresent(vehicle -> {
                    booking.setVehicleBrand(vehicle.getBrand());
                    booking.setVehicleModel(vehicle.getModel());
                });
            }

            req.setAttribute("bookings", bookings);
            req.getRequestDispatcher("/jsp/viewBookings.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }

    }
}
