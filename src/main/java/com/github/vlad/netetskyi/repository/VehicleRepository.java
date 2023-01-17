package com.github.vlad.netetskyi.repository;

import com.github.vlad.netetskyi.entity.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private static VehicleRepository repository = null;
    private DataSource dataSource;

    public static synchronized VehicleRepository getInstance() {
        if (repository == null) {
            repository = new VehicleRepository();
        }

        return repository;
    }

    public VehicleRepository() {
        this.dataSource = DataSourceFactory.getInstance();
    }

    public void add(Vehicle vehicle) {
        final String sql = "INSERT INTO car_rental_sh.vehicles(brand, model, type, production_year, price, img) VALUES(?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vehicle.getBrand());
            pstmt.setString(2, vehicle.getModel());
            pstmt.setString(3, vehicle.getType());
            pstmt.setInt(4, vehicle.getYear());
            pstmt.setDouble(5, vehicle.getPrice());
            pstmt.setBytes(6, vehicle.getImg());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> getAll() {
        final String sql = "SELECT * FROM car_rental_sh.vehicles LIMIT 100";
        final List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Vehicle vehicle = new Vehicle(
                        resultSet.getLong("vehicle_id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("type"),
                        resultSet.getInt("production_year"),
                        resultSet.getDouble("price"),
                        resultSet.getBytes("img"));
                vehicles.add(vehicle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}
