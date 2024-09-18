package com.hrs.hotel_service.repository;

import com.hrs.hotel_service.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserIdAndHotelId(@Param("user_id") Long userId, @Param("hotel_id") Long hotelId);
}