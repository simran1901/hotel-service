CREATE DATABASE IF NOT EXISTS hotel_booking;
USE hotel_booking;

-- drop table users;
-- drop table hotels;
-- drop table bookings;

CREATE TABLE IF NOT EXISTS users (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(100),
                       email VARCHAR(100) UNIQUE,
                       phone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS hotels (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(100),
                        location VARCHAR(100),
                        available_rooms INT
);

CREATE TABLE IF NOT EXISTS bookings (
                          id BIGINT PRIMARY KEY,
                          user_id INT REFERENCES users(id),
                          hotel_id INT REFERENCES hotels(id),
                          check_in TIMESTAMP,
                          check_out TIMESTAMP,
                          status VARCHAR(20) DEFAULT 'CONFIRMED'
);

-- Inserting sample users
INSERT IGNORE INTO users (name, email, phone) VALUES ('Alice Smith', 'alice@example.com', '555-1234');
INSERT IGNORE INTO users (name, email, phone) VALUES ('Bob Johnson', 'bob@example.com', '555-5678');
INSERT IGNORE INTO users (name, email, phone) VALUES ('Charlie Brown', 'charlie@example.com', '555-8765');
INSERT IGNORE INTO users (name, email, phone) VALUES ('Diana Prince', 'diana@example.com', '555-4321');
INSERT IGNORE INTO users (name, email, phone) VALUES ('Ethan Hunt', 'ethan@example.com', '555-2468');

-- Inserting sample hotels
INSERT IGNORE INTO hotels (name, location, available_rooms) VALUES ('Luxury Inn', 'Los Angeles', 50);
INSERT IGNORE INTO hotels (name, location, available_rooms) VALUES ('Cozy Cottage', 'Lake Tahoe', 20);
INSERT IGNORE INTO hotels (name, location, available_rooms) VALUES ('Mountain View Hotel', 'Denver', 30);
INSERT IGNORE INTO hotels (name, location, available_rooms) VALUES ('Seaside Resort', 'Miami', 100);
INSERT IGNORE INTO hotels (name, location, available_rooms) VALUES ('Urban Oasis', 'New York', 40);

-- Inserting sample bookings
INSERT IGNORE INTO bookings (user_id, hotel_id, check_in, check_out, status) VALUES (1, 1, '2024-10-01 15:00:00', '2024-10-05 11:00:00', 'CONFIRMED');
INSERT IGNORE INTO bookings (user_id, hotel_id, check_in, check_out, status) VALUES (2, 2, '2024-11-10 15:00:00', '2024-11-15 11:00:00', 'CONFIRMED');
INSERT IGNORE INTO bookings (user_id, hotel_id, check_in, check_out, status) VALUES (3, 3, '2024-12-20 15:00:00', '2024-12-25 11:00:00', 'CONFIRMED');
INSERT IGNORE INTO bookings (user_id, hotel_id, check_in, check_out, status) VALUES (4, 4, '2024-09-05 15:00:00', '2024-09-10 11:00:00', 'CONFIRMED');
INSERT IGNORE INTO bookings (user_id, hotel_id, check_in, check_out, status) VALUES (5, 5, '2024-09-15 15:00:00', '2024-09-20 11:00:00', 'CONFIRMED');