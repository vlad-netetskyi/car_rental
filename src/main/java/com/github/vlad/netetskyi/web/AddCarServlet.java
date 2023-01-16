package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Vehicle;
import com.github.vlad.netetskyi.repository.VehicleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "AddCar",
        urlPatterns = {"/addCar"}
)
public class AddCarServlet extends HttpServlet {

    private final VehicleRepository vehicleRepository;

    public AddCarServlet() {
        vehicleRepository = VehicleRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/addCar GET");
        req.getRequestDispatcher("/jsp/addCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/addCar POST");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        Vehicle vehicle = new Vehicle(brand, model);
        vehicleRepository.add(vehicle);
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
