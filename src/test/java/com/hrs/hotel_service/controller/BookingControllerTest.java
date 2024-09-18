package com.hrs.hotel_service.controller;

import com.hrs.hotel_service.entity.Booking;
import com.hrs.hotel_service.enums.BOOKING_STATUS;
import com.hrs.hotel_service.exception.BookingConflictException;
import com.hrs.hotel_service.exception.ResourceNotFoundException;
import com.hrs.hotel_service.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private Booking booking;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        booking = new Booking(1L, null, null, LocalDateTime.now(), LocalDateTime.now().plusDays(5), BOOKING_STATUS.CONFIRMED);
    }

    @Test
    public void testCreateBooking() {
        when(bookingService.createBooking(any(Booking.class))).thenReturn(booking);
        ResponseEntity<Booking> response = bookingController.createBooking(booking);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(booking, response.getBody());
    }

    @Test
    public void testGetBooking() {
        when(bookingService.getBooking(1L)).thenReturn(booking);
        ResponseEntity<Booking> response = bookingController.getBooking(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(booking, response.getBody());
    }

    @Test
    public void testGetAllBookings() {
        when(bookingService.getAllBookings()).thenReturn(Collections.singletonList(booking));
        ResponseEntity<List<Booking>> response = bookingController.getAllBookings();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void testCancelBooking() {
        ResponseEntity<Void> response = bookingController.cancelBooking(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookingService, times(1)).cancelBooking(1L);
    }

    @Test
    public void testCreateBooking_Conflict() {
        when(bookingService.createBooking(any(Booking.class))).thenThrow(new BookingConflictException("Booking conflict detected"));
        ResponseEntity<Booking> response = bookingController.createBooking(booking);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testUpdateBooking_NotFound() {
        when(bookingService.updateBooking(anyLong(), any(Booking.class))).thenThrow(new ResourceNotFoundException("Booking not found"));
        ResponseEntity<Booking> response = bookingController.updateBooking(1L, booking);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetBooking_NotFound() {
        when(bookingService.getBooking(1L)).thenThrow(new ResourceNotFoundException("Booking not found"));
        ResponseEntity<Booking> response = bookingController.getBooking(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCancelBooking_NotFound() {
        doThrow(new ResourceNotFoundException("Booking not found")).when(bookingService).cancelBooking(1L);
        ResponseEntity<Void> response = bookingController.cancelBooking(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}