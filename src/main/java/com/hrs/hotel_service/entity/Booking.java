package com.hrs.hotel_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id", nullable = false)
    private Long hotelId;

    @Column(name = "check_in", nullable = false)
    private Date checkIn;

    @Column(name = "check_out", nullable = false)
    private Date checkOut;

    private int guests;

    private int rooms;

    @Column(name = "personal_details")
    private String personalDetails;
}