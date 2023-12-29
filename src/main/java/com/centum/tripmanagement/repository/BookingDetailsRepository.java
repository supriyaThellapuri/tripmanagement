package com.centum.tripmanagement.repository;

import com.centum.tripmanagement.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
}
