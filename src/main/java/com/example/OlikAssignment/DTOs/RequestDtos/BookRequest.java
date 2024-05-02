package com.example.OlikAssignment.DTOs.RequestDtos;


import com.example.OlikAssignment.Models.Author;
import lombok.Data;

@Data
public class BookRequest {

    private String title;


    private int publicationYear;
    private String authorName;
}
