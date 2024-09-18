package com.hrs.hotel_service.service;

import com.hrs.hotel_service.entity.Hotel;
import com.hrs.hotel_service.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotel(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }
}