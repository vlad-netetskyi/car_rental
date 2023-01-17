package com.github.vlad.netetskyi.web;

import com.github.vlad.netetskyi.entity.Vehicle;
import com.github.vlad.netetskyi.repository.VehicleRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(
        name = "AddCar",
        urlPatterns = {"/addCar"}
)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
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
        Part filePart = req.getPart("car_img");
        InputStream fileContent = filePart.getInputStream();
        byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);

        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String type = req.getParameter("type");
        int year = Integer.parseInt(req.getParameter("year"));
        double price = Double.parseDouble(req.getParameter("price"));
        Vehicle vehicle = new Vehicle(null, brand, model, type, year, price, fileAsByteArray);
        vehicleRepository.add(vehicle);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
