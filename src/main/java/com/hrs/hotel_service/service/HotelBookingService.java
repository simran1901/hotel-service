package com.hrs.hotel_service.service;

import com.hrs.hotel_service.entity.Booking;
import com.hrs.hotel_service.entity.Hotel;
import com.hrs.hotel_service.repository.BookingRepository;
import com.hrs.hotel_service.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelBookingService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Hotel> searchHotels(String location) {
        // Implement your search logic here
        return hotelRepository.findAll(); // Replace with actual search logic
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long bookingId, Booking booking) {
        if (bookingRepository.existsById(bookingId)) {
            booking.setId(bookingId);
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("Booking not found");
    }

    public Booking viewBooking(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}