package com.hrs.hotel_service.service;

import com.hrs.hotel_service.entity.Booking;
import com.hrs.hotel_service.exception.BookingConflictException;
import com.hrs.hotel_service.exception.ResourceNotFoundException;
import com.hrs.hotel_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        // Example of checking for conflicting bookings
        List<Booking> existingBookings = bookingRepository.findByUserIdAndHotelId(booking.getUser().getId(), booking.getHotel().getId());
        if (!existingBookings.isEmpty()) {
            throw new BookingConflictException("Booking conflict detected for this user and hotel.");
        }
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking booking) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with ID: " + id);
        }
        booking.setId(id);
        return bookingRepository.save(booking);
    }

    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void cancelBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }
}