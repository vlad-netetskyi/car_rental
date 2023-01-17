CREATE SCHEMA IF NOT exists car_rental_sh;
CREATE TABLE IF NOT EXISTS car_rental_sh.users (
    user_id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    role VARCHAR(20) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS car_rental_sh.vehicles (
    vehicle_id serial PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    type VARCHAR(20) NOT NULL,
    production_year INT NOT NULL,
    price NUMERIC NOT NULL,
    img BYTEA NOT NULL
);