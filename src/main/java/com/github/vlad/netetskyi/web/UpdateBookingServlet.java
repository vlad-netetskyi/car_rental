package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.repository.BookingRepository;
import com.github.vlad.netetskyi.service.security.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;

@WebServlet(name = "UpdateBooking", urlPatterns = {"/updateBooking"})
public class UpdateBookingServlet extends HttpServlet {

    private final BookingRepository bookingRepository;

    public UpdateBookingServlet() {
        this.bookingRepository = BookingRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/updateBookings GET");
        User user = ((User) req.getSession().getAttribute("user"));
        if (user != null && user.isAdmin()) {
            long id = Long.parseLong(req.getParameter("id"));
            String status = req.getParameter("status");
            System.out.printf("Updating bookings %d with status %s\n", id, status);
            bookingRepository.updateStatus(id, status, Instant.now());
            // forward to thank you page with order id
            resp.sendRedirect(req.getContextPath() + "/bookings");
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
