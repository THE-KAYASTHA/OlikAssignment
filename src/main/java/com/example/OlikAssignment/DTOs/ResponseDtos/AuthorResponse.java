package com.example.OlikAssignment.DTOs.ResponseDtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthorResponse {
    private int authorId;

    private String authorName;

    private String biography;

    private List<String> bookName;

}
