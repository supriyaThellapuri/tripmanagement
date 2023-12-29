package com.centum.tripmanagement.repository;

import com.centum.tripmanagement.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>
{
    @Query("SELECT COUNT(b) FROM UserDetails u JOIN u.bookings b WHERE u.firstName = :firstName")
    long getCountOfTripsByUsername(@Param("firstName") String firstName);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserDetails u WHERE u.id = :userId AND u.paymentConfirmed = true")
    boolean isPaymentConfirmed(@Param("userId") Long userId);

    UserDetails getUserByPhoneNumber(String phoneNumber);


}
