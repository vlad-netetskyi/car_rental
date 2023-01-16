package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Vehicle;
import com.github.vlad.netetskyi.repository.VehicleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        List<Vehicle> all = vehicleRepository.getAll();
        req.setAttribute("vehicles", all);
        req.getRequestDispatcher("/jsp/viewVehicles.jsp").forward(req, resp);
    }
}
