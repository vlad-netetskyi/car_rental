package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Booking;
import com.github.vlad.netetskyi.entity.Vehicle;
import com.github.vlad.netetskyi.repository.BookingRepository;
import com.github.vlad.netetskyi.repository.VehicleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.github.vlad.netetskyi.web.ViewCarsServlet.parse;

@WebServlet(name = "BookCar", urlPatterns = {"/bookCar"})
public class BookCarServlet extends HttpServlet {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;

    public BookCarServlet() {
        this.bookingRepository = BookingRepository.getInstance();
        this.vehicleRepository = VehicleRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/bookCar GET");
        // extract ids
        long userId = Long.parseLong(req.getParameter("userId"));
        long vehicleId = Long.parseLong(req.getParameter("vehicleId"));
        LocalDate fromDate = parse(req.getParameter("fromDate"));
        LocalDate toDate = parse(req.getParameter("toDate"));

        Vehicle vehicle = vehicleRepository.getByIds(List.of(vehicleId)).get(0);
        long rentDuration = ChronoUnit.DAYS.between(fromDate, toDate);
        double totalPrice = vehicle.getPrice() * rentDuration;

        // create pending order
        Booking booking = new Booking(null, userId, vehicleId, Instant.now(), fromDate, toDate, totalPrice, Booking.PENDING, Instant.now());
        System.out.println("Adding new pending order:\n " + booking);
        bookingRepository.save(booking);
        // forward to thank you page with order id
        req.getRequestDispatcher("/jsp/bookingCompleted.jsp").forward(req, resp);
    }
}
