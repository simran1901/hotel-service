package com.hrs.hotel_service.entity;

import com.hrs.hotel_service.enums.BOOKING_STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hotel hotel;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BOOKING_STATUS status; // e.g., "CONFIRMED", "CANCELLED"
}