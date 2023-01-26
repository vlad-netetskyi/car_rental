package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Booking;
import com.github.vlad.netetskyi.repository.BookingRepository;
import com.github.vlad.netetskyi.service.security.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserBookings", urlPatterns = {"/bookings"})
public class UserBookingsServlet extends HttpServlet {

    private final BookingRepository bookingRepository;

    public UserBookingsServlet() {
        this.bookingRepository = BookingRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ((User) req.getSession().getAttribute("user"));
        if (user != null) {
            List<Booking> all = bookingRepository.getAllByUserId(user.getId());
            req.setAttribute("bookings", all);
            req.getRequestDispatcher("/jsp/viewBookings.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }

    }
}
