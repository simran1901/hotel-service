package com.hrs.hotel_service.service;

import com.hrs.hotel_service.entity.Booking;
import com.hrs.hotel_service.entity.Hotel;
import com.hrs.hotel_service.entity.User;
import com.hrs.hotel_service.enums.BOOKING_STATUS;
import com.hrs.hotel_service.exception.BookingConflictException;
import com.hrs.hotel_service.exception.ResourceNotFoundException;
import com.hrs.hotel_service.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking;
    private User user;
    private Hotel hotel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "John Doe", "john@example.com", "1234567890");
        hotel = new Hotel(1L, "Grand Hotel", "New York", 100);
        booking = new Booking(1L, user, hotel, LocalDateTime.now(), LocalDateTime.now().plusDays(5), BOOKING_STATUS.CONFIRMED);
    }

    @Test
    public void testCreateBooking() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        Booking createdBooking = bookingService.createBooking(booking);
        assertEquals(BOOKING_STATUS.CONFIRMED, createdBooking.getStatus());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    public void testGetBooking() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        Booking foundBooking = bookingService.getBooking(1L);
        assertNotNull(foundBooking);
        assertEquals("John Doe", foundBooking.getUser().getName());
    }

    @Test
    public void testCancelBooking() {
        bookingService.cancelBooking(1L);
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCreateBooking_Conflict() {
        when(bookingRepository.findByUserIdAndHotelId(anyLong(), anyLong())).thenReturn(Arrays.asList(booking));
        assertThrows(BookingConflictException.class, () -> bookingService.createBooking(booking));
    }

    @Test
    public void testUpdateBooking_NotFound() {
        when(bookingRepository.existsById(1L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> bookingService.updateBooking(1L, booking));
    }

    @Test
    public void testGetBooking_NotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> bookingService.getBooking(1L));
    }

    @Test
    public void testCancelBooking_NotFound() {
        when(bookingRepository.existsById(1L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> bookingService.cancelBooking(1L));
    }
}