CREATE SCHEMA IF NOT exists car_rental_sh;
CREATE TABLE IF NOT EXISTS car_rental_sh.users (
    user_id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    role VARCHAR(20) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);
INSERT INTO car_rental_sh.users(username, password, role, first_name, last_name)
    VALUES("admin", sha512("admin"), "ADMIN", "admin", "admin");