package com.centum.tripmanagement.repository;

import com.centum.tripmanagement.entity.TripDetails;
import com.centum.tripmanagement.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripDetailsRepository extends JpaRepository<TripDetails, Long>
{
    @Query("SELECT t.bookings FROM TripDetails t WHERE t.id = :tripId")
    List<UserDetails> getEnrolledUsersForTrip(@Param("tripId") Long tripId);

    List<TripDetails> findAll();



    @Query("SELECT COUNT(b) FROM TripDetails t JOIN t.bookings b WHERE t.id = :tripId")
    long getBookingsCountForTrip(@Param("tripId") Long tripId);

    @Query("SELECT t FROM TripDetails t WHERE t.kms1 >= :minKms AND t.kms2 <= :maxKms")
    List<TripDetails> getTripsByKms(@Param("minKms") int minKms, @Param("maxKms") int maxKms);
}
