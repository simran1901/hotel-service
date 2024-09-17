package com.hrs.hotel_service.controller;

import com.hrs.hotel_service.entity.Booking;
import com.hrs.hotel_service.entity.Hotel;
import com.hrs.hotel_service.service.HotelBookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelBookingController {

    @Autowired
    private HotelBookingService hotelBookingService;

    @Operation(description = "API to search hotels based on location which is an optional parameter")
    @GetMapping("/hotels")
    public List<Hotel> searchHotels(@RequestParam String location) {
        return hotelBookingService.searchHotels(location);
    }

    @Operation(description = "API to book hotel room(s) using hotel and user details")
    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking booking) {
        return hotelBookingService.createBooking(booking);
    }

    @Operation(description = "API to update a booking details using bookingId")
    @PutMapping("/bookings/{bookingId}")
    public Booking updateBooking(@PathVariable Long bookingId, @RequestBody Booking booking) {
        return hotelBookingService.updateBooking(bookingId, booking);
    }

    @Operation(description = "API to view any booking's details using bookingId")
    @GetMapping("/bookings/{bookingId}")
    public Booking viewBooking(@PathVariable Long bookingId) {
        return hotelBookingService.viewBooking(bookingId);
    }

    @Operation(description = "API to delete any booking using bookingId")
    @DeleteMapping("/bookings/{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId) {
        hotelBookingService.cancelBooking(bookingId);
    }
}