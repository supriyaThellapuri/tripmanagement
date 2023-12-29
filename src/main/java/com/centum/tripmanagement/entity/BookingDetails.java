
package com.centum.tripmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "BOOKING_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date bookingDate;
    private int numberOfPeople;
    private String specialRequests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_details_id")
    @JsonBackReference
    private TripDetails tripDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id")
    @JsonBackReference
    private UserDetails userDetails;

    @OneToOne(mappedBy = "bookingDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private PaymentTable paymentTable;
}

