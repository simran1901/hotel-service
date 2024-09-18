package com.hrs.hotel_service.controller;

import com.hrs.hotel_service.entity.Booking;
import com.hrs.hotel_service.entity.Hotel;
import com.hrs.hotel_service.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Operation(summary = "Get all hotels",
            description = "Retrieves a list of all hotels in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of hotels retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hotel.class))))
    })
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @Operation(summary = "Get a hotel by ID",
            description = "Retrieves a specific hotel using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel found",
                    content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "Hotel not found",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotel(id);
        return hotel != null ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
    }
}