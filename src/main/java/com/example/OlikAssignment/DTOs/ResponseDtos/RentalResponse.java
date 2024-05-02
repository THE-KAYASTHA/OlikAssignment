package com.example.OlikAssignment.DTOs.ResponseDtos;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RentalResponse {
    private int rentalId;

    private String rentalName;

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private boolean bookReturned;

    private int bookId;

}
