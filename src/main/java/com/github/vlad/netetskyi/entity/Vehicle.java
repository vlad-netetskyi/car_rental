package com.github.vlad.netetskyi.entity;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Vehicle {
    private final String brand;
    private final String model;

    private final byte[] img;

    public Vehicle(String brand, String model, byte[] img) {
        this.brand = brand;
        this.model = model;
        this.img = img;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
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
        return Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
