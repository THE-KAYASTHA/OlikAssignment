package com.example.OlikAssignment.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="Rentals")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Rental {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;

    private String rentalName;

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private boolean bookReturned;

    @JoinColumn
    @ManyToOne
    private Book book;

}
