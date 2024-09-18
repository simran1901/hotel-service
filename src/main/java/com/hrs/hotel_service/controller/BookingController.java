package com.hrs.hotel_service.controller;

import com.hrs.hotel_service.entity.Booking;
import com.hrs.hotel_service.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Operation(summary = "Create a new hotel booking",
            description = "This endpoint allows a user to create a new hotel booking. " +
                    "Ensure that the user and hotel IDs are valid and that the booking does not conflict with existing reservations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking created successfully",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "409", description = "Booking conflict detected",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @Parameter(description = "Booking details to be created", required = true)
            @RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing booking",
            description = "Updates the details of a booking based on the provided booking ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @Parameter(description = "ID of the booking to be updated", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated booking details", required = true)
            @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(id, booking));
    }

    @Operation(summary = "Get a booking by ID",
            description = "Retrieves a specific booking using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(
            @Parameter(description = "ID of the booking to retrieve", required = true)
            @PathVariable Long id) {
        Booking booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }

    @Operation(summary = "Get all bookings",
            description = "Retrieves a list of all bookings in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of bookings retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Booking.class))))
    })
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Operation(summary = "Cancel a booking",
            description = "Cancels a booking by its unique ID. This action cannot be undone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Booking canceled successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(
            @Parameter(description = "ID of the booking to cancel", required = true)
            @PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}