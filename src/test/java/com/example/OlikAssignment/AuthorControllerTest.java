package com.example.OlikAssignment;

import com.example.OlikAssignment.Controller.AuthorController;
import com.example.OlikAssignment.DTOs.RequestDtos.AuthorRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.Service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
@AutoConfigureMockMvc
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    void testAddAuthor() throws Exception {
        AuthorRequest request = new AuthorRequest();
        request.setAuthorName("John Doe");
        request.setBiography("Some biography");

        when(authorService.addAuthor(request)).thenReturn("Author added successfully");

        mockMvc.perform(post("/author/addAuthor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"authorName\":\"John Doe\",\"biography\":\"Some biography\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Author added successfully"));
    }

    @Test
    void testGetBooksByAuthorName() throws Exception {
        BookResponse bookResponse = BookResponse.builder()
                .title("Book 1")
                .authorName("John Doe")
                .build();

        when(authorService.getBooksByAuthorName("John Doe")).thenReturn(Collections.singletonList(bookResponse));

        mockMvc.perform(get("/author/getBooksByAuthorName")
                        .param("authorName", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[0].authorName").value("John Doe"));
    }

    @Test
    void testDeleteAuthor() throws Exception {
        when(authorService.deleteAuthor("John Doe")).thenReturn("Author deleted successfully");

        mockMvc.perform(delete("/author/deleteAuthor")
                        .param("authorName", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Author deleted successfully"));
    }
}
