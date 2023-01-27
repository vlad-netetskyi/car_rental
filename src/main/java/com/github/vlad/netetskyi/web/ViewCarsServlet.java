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
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ViewCars", urlPatterns = {"/"})
public class ViewCarsServlet extends HttpServlet {

    private final VehicleRepository vehicleRepository;
    private final BookingRepository bookingRepository;

    public ViewCarsServlet() {
        this.vehicleRepository = VehicleRepository.getInstance();
        this.bookingRepository = BookingRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/cars GET");
        final String pattern = "YYYY-MM-DD";
        req.setAttribute("defaultFromDate", Booking.friendlyDate(Instant.now(), pattern));
        req.setAttribute("defaultToDate", Booking.friendlyDate(Instant.now().plus(1, ChronoUnit.DAYS), pattern));
        req.getRequestDispatcher("/jsp/searchVehicles.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/cars POST");
        Instant fromDate = parse(req.getParameter("fromDate"));
        Instant toDate = parse(req.getParameter("toDate"));
        System.out.println("FromDate = " + fromDate + ", ToDate = " + toDate);

        List<Booking> bookings = bookingRepository.getAll();
        List<Long> bookedVehicleIds = bookings.stream()
                .filter(booking -> !Objects.equals(Booking.REJECTED, booking.getStatus()))
                .filter(booking -> isWithin(fromDate, booking.getRentStartDate(), booking.getRentFinishDate()) || isWithin(toDate, booking.getRentStartDate(), booking.getRentFinishDate()))
                .map(Booking::getVehicleId).toList();


        List<Vehicle> all = vehicleRepository.getAllWithFilters(bookedVehicleIds);
        req.setAttribute("vehicles", all);
        req.setAttribute("fromDate", req.getParameter("fromDate"));
        req.setAttribute("toDate", req.getParameter("toDate"));
        req.getRequestDispatcher("/jsp/viewVehicles.jsp").forward(req, resp);
    }

    private static boolean isWithin(Instant instant, Instant from, Instant to) {
        return instant.isAfter(from) && instant.isBefore(to);
    }

    public static Instant parse(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return date.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
    }
}
