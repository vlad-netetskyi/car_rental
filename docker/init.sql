CREATE SCHEMA IF NOT exists car_rental_sh;
CREATE TABLE IF NOT EXISTS car_rental_sh.users (
    user_id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    role VARCHAR(20) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone_number VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS car_rental_sh.vehicles (
    vehicle_id serial PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL,
    production_year INT NOT NULL,
    price NUMERIC NOT NULL,
    fuel VARCHAR(20) NOT NULL,
    engine_capacity NUMERIC NOT NULL,
    transmission VARCHAR(20) NOT NULL,
    seats NUMERIC NOT NULL,
    city VARCHAR(20) NOT NULL,
    img BYTEA NOT NULL
);
CREATE TABLE IF NOT EXISTS car_rental_sh.orders (
    id serial PRIMARY KEY,
    vehicle_id INT NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    rent_start_date TIMESTAMP NOT NULL,
    rent_finish_date TIMESTAMP NOT NULL,
    rent_total_price NUMERIC NOT NULL,
    status VARCHAR(30) NOT NULL,
    status_changed_at TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES car_rental_sh.vehicles(vehicle_id),
    FOREIGN KEY (user_id) REFERENCES car_rental_sh.users(user_id)
);