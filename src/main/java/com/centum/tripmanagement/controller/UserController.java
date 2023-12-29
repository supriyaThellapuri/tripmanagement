package com.centum.tripmanagement.controller;

import com.centum.tripmanagement.entity.BookingDetails;
import com.centum.tripmanagement.entity.TripDetails;
import com.centum.tripmanagement.entity.UserDetails;
import com.centum.tripmanagement.repository.BookingDetailsRepository;
import com.centum.tripmanagement.repository.TripDetailsRepository;
import com.centum.tripmanagement.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usertrip")  // Fixed the 'name' attribute to 'value'
public class UserController {

    @Autowired
    public BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    public TripDetailsRepository tripDetailsRepository;

    @Autowired
    public UserDetailsRepository userDetailsRepository;

    @PostMapping(value = "/book-trip")
    @Transactional
    public ResponseEntity<String> bookTrip(@RequestBody BookingDetails bookingRequest) {
        Long userId = bookingRequest.getUserDetails().getId();  // Fixed the 'getId' method
        Long tripId = bookingRequest.getTripDetails().getId();  // Fixed the 'getId' method

        Optional<UserDetails> userDetailsOptional = userDetailsRepository.findById(userId);
        Optional<TripDetails> tripDetailsOptional = tripDetailsRepository.findById(tripId);

        if (userDetailsOptional.isPresent() && tripDetailsOptional.isPresent()) {
            UserDetails userDetails = userDetailsOptional.get();
            TripDetails tripDetails = tripDetailsOptional.get();

            // Create a new booking
            BookingDetails newBookingDetails = new BookingDetails();
            newBookingDetails.setUserDetails(userDetails);
            newBookingDetails.setTripDetails(tripDetails);

            // Save the booking
            bookingDetailsRepository.save(newBookingDetails);

            return ResponseEntity.ok("Booking successful");
        } else {
            return ResponseEntity.badRequest().body("User or trip not found");

        }
    }

    @GetMapping("/count-of-trips/{username}")
    public ResponseEntity<Long> countOfTrips(@PathVariable String username) {
        long count = userDetailsRepository.getCountOfTripsByUsername(username);
        return ResponseEntity.ok(count);
    }



    @GetMapping("/enrolled-users/{tripId}")
    public ResponseEntity<List<UserDetails>> enrolledUsers(@PathVariable Long tripId) {
        List<UserDetails> enrolledUsers = tripDetailsRepository.getEnrolledUsersForTrip(tripId);
        return ResponseEntity.ok(enrolledUsers);
    }

    // 3) Enter the phone number to get user details
    @GetMapping("/user-by-phone/{phoneNumber}")
    public ResponseEntity<UserDetails> userByPhoneNumber(@PathVariable String phoneNumber) {
        UserDetails userDetails = userDetailsRepository.getUserByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userDetails);
    }



    // 4) Payment confirmation based on the userId
    @GetMapping("/payment-confirmation/{userId}")
    public ResponseEntity<Boolean> paymentConfirmation(@PathVariable Long userId) {
        boolean isPaymentConfirmed = userDetailsRepository.isPaymentConfirmed(userId);
        return ResponseEntity.ok(isPaymentConfirmed);
    }

    // 5) Display all the trips
    @GetMapping("/all-trips")
    public ResponseEntity<List<TripDetails>> allTrips() {
        List<TripDetails> allTrips = tripDetailsRepository.findAll();
        return ResponseEntity.ok(allTrips);
    }

    // 6) Display trips based on kms (min and max)
    @GetMapping("/trips-by-kms/{minKms}/{maxKms}")
    public ResponseEntity<List<TripDetails>> tripsByKms(@PathVariable int minKms, @PathVariable int maxKms) {
        List<TripDetails> tripsByKms = tripDetailsRepository.getTripsByKms(minKms, maxKms);
        return ResponseEntity.ok(tripsByKms);
    }

    // 7) Number of bookings based on a particular trip
    @GetMapping("/bookings-count/{tripId}")
    public ResponseEntity<Long> bookingsCount(@PathVariable Long tripId) {
        long bookingsCount = tripDetailsRepository.getBookingsCountForTrip(tripId);
        return ResponseEntity.ok(bookingsCount);
    }

    // 8) If you enter a user id, get user details, trip details, booking details classes
    @GetMapping("/user-details/{userId}")
    public ResponseEntity<UserDetails> userDetailsTripDetailsBookingDetails(@PathVariable Long userId) {
        Optional<UserDetails> userDetailsOptional = userDetailsRepository.findById(userId);
        if (userDetailsOptional.isPresent()) {
            UserDetails userDetails = userDetailsOptional.get();
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

