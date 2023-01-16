package com.github.vlad.netetskyi.repository;

import com.github.vlad.netetskyi.entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private static VehicleRepository repository = null;
    private List<Vehicle> vehicles;

    public static synchronized VehicleRepository getInstance() {
        if (repository == null) {
            repository = new VehicleRepository();
        }

        return repository;
    }

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    public void add(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    public List<Vehicle> getAll() {
        return new ArrayList<>(this.vehicles);
    }
}
