package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Vehicle;
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
import java.util.List;

@WebServlet(name = "ViewCars", urlPatterns = {"/"})
public class ViewCarsServlet extends HttpServlet {

    private final VehicleRepository vehicleRepository;

    public ViewCarsServlet() {
        this.vehicleRepository = VehicleRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/cars GET");
        req.getRequestDispatcher("/jsp/searchVehicles.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/cars POST");
        Instant fromDate = parse(req.getParameter("fromDate"));
        Instant toDate = parse(req.getParameter("toDate"));
        System.out.println("FromDate = " + fromDate + ", ToDate = " + toDate);
        List<Vehicle> all = vehicleRepository.getAll();
        req.setAttribute("vehicles", all);
        req.setAttribute("fromDate", req.getParameter("fromDate"));
        req.setAttribute("toDate", req.getParameter("toDate"));
        req.getRequestDispatcher("/jsp/viewVehicles.jsp").forward(req, resp);
    }

    public static Instant parse(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return date.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
    }
}
