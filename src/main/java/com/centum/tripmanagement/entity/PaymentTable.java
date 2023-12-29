package com.centum.tripmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Payment_Table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String paymentMethod;
    private Date paymentDate;
    private String transactionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_details_id")
    @JsonManagedReference
    private BookingDetails bookingDetails;
}



