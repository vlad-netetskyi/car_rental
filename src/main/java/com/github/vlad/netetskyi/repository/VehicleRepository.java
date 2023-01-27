package com.github.vlad.netetskyi.repository;

import com.github.vlad.netetskyi.entity.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                vehicles.add(map(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> getAllWithFilters(List<Long> idsToExclude) {
        String sql = "SELECT * FROM car_rental_sh.vehicles";

        if (idsToExclude != null && !idsToExclude.isEmpty()) {
            sql += String.format(" WHERE vehicle_id NOT IN (%s)",
                    idsToExclude.stream()
                            .map(v -> "?")
                            .collect(Collectors.joining(", ")));
        }

        final List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;

            if (idsToExclude != null && !idsToExclude.isEmpty()) {
                for (Long id : idsToExclude) {
                    pstmt.setLong(index++, id);
                }
            }

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                vehicles.add(map(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> getByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        var sql = String.format("SELECT * FROM car_rental_sh.vehicles WHERE vehicle_id IN (%s)",
                ids.stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(", ")));
        final List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (Long id : ids) {
                pstmt.setLong(index++, id);
            }
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                vehicles.add(map(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    private Vehicle map(ResultSet resultSet) throws SQLException {
        return new Vehicle(
                resultSet.getLong("vehicle_id"),
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getString("type"),
                resultSet.getInt("production_year"),
                resultSet.getDouble("price"),
                resultSet.getBytes("img"));
    }
}
