package com.example.OlikAssignment.DTOs.ResponseDtos;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookResponse {

    private int bookId;
    private boolean isBookAvailable;
    private int publicationYear;
    private String authorName;
    private String title;


}
