package com.centum.tripmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TRIP_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int kms1;
    private int kms2;
    private String tripName;
    private String destination;
    private Date startDate;
    private Date endDate;
    private String description;

    @OneToMany(mappedBy = "tripDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<BookingDetails> bookings;
}
