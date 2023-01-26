package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Booking;
import com.github.vlad.netetskyi.repository.BookingRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;

import static com.github.vlad.netetskyi.web.ViewCarsServlet.parse;

@WebServlet(name = "BookCar", urlPatterns = {"/bookCar"})
public class BookCarServlet extends HttpServlet {

    private final BookingRepository bookingRepository;

    public BookCarServlet() {
        this.bookingRepository = BookingRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/bookCar GET");
        // extract ids
        long userId = Long.parseLong(req.getParameter("userId"));
        long vehicleId = Long.parseLong(req.getParameter("vehicleId"));
        Instant fromDate = parse(req.getParameter("fromDate"));
        Instant toDate = parse(req.getParameter("toDate"));

        // create pending order
        Booking booking = new Booking(null, userId, vehicleId, Instant.now(), fromDate, toDate, 100.0, "PENDING", Instant.now());
        System.out.println("Adding new pending order:\n " + booking);
        bookingRepository.save(booking);
        // forward to thank you page with order id
        req.getRequestDispatcher("/jsp/bookingCompleted.jsp").forward(req, resp);
    }
}
