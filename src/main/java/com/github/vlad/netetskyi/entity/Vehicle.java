package com.github.vlad.netetskyi.entity;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class Vehicle {
    private final Long id;
    private final String brand;
    private final String model;
    private final String type;
    private final int year;
    private final double price;
    private final String transmission;
    private final String fuel;
    private final double engineCapacity;
    private final int seats;
    private final String city;
    private final byte[] img;

    public Vehicle(Long id, String brand, String model, String type, int year, double price, String transmission, String fuel, double engineCapacity, int seats, String city, byte[] img) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = year;
        this.price = price;
        this.transmission = transmission;
        this.fuel = fuel;
        this.engineCapacity = engineCapacity;
        this.seats = seats;
        this.img = img;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public byte[] getImg() {
        return img;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public String getFuel() {
        return fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public int getSeats() {
        return seats;
    }

    public String getCity() {
        return city;
    }

    public String getBase64ImgFile() throws UnsupportedEncodingException {
        // TODO: find better place for it
        byte[] encodeBase64 = Base64.encodeBase64(img, false);
        return new String(encodeBase64, StandardCharsets.UTF_8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year && Double.compare(vehicle.price, price) == 0 && Objects.equals(id, vehicle.id) && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model) && Objects.equals(type, vehicle.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, type, year, price);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", transmission='" + transmission + '\'' +
                ", fuel='" + fuel + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", seats=" + seats +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
