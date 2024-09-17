CREATE DATABASE IF NOT EXISTS hotel_booking_db;
USE hotel_booking_db;

CREATE TABLE IF NOT EXISTS hotel (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       location VARCHAR(255) NOT NULL,
                       amenities VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS booking (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         hotel_id BIGINT NOT NULL,
                         check_in DATE NOT NULL,
                         check_out DATE NOT NULL,
                         guests INT NOT NULL,
                         rooms INT NOT NULL,
                         personal_details VARCHAR(255),
                         FOREIGN KEY (hotel_id) REFERENCES hotel(id) ON DELETE CASCADE
);

-- inserting sample data into hotel table
INSERT IGNORE INTO hotel (name, location, amenities)
VALUES ('Grand Plaza Hotel', 'New York', 'Free WiFi, Pool, Gym');

INSERT IGNORE INTO hotel (name, location, amenities)
VALUES ('Seaside Resort', 'Miami', 'Beach Access, Spa, Breakfast Included');

INSERT IGNORE INTO hotel (name, location, amenities)
VALUES ('Mountain Lodge', 'Denver', 'Hiking Trails, Free Parking, Pet Friendly');

-- inserting sample data into booking table
INSERT IGNORE INTO booking (hotel_id, check_in, check_out, guests, rooms, personal_details)
VALUES (1, '2024-10-01', '2024-10-05', 2, 1, 'John Doe, john.doe@example.com, 555-1234');

INSERT IGNORE INTO booking (hotel_id, check_in, check_out, guests, rooms, personal_details)
VALUES (2, '2024-11-15', '2024-11-20', 4, 2, 'Jane Smith, jane.smith@example.com, 555-5678');

INSERT INTO booking (hotel_id, check_in, check_out, guests, rooms, personal_details)
VALUES (3, '2024-12-10', '2024-12-15', 1, 1, 'Alice Johnson, alice.johnson@example.com, 555-8765');

