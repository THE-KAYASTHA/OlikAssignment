package com.example.OlikAssignment;

import com.example.OlikAssignment.Controller.BookController;
import com.example.OlikAssignment.DTOs.RequestDtos.BookRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.Service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testAddBook() throws Exception {
        BookRequest request = new BookRequest();
        request.setTitle("Example Book");
        request.setPublicationYear(2022);
        request.setAuthorName("John Doe");

        when(bookService.addBook(request)).thenReturn("Book added successfully");

        mockMvc.perform(post("/book/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Example Book\",\"publicationYear\":2022,\"authorName\":\"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book added successfully"));
    }

    @Test
    void testGetBook() throws Exception {
        BookResponse bookResponse = BookResponse.builder()
                .title("Example Book")
                .publicationYear(2022)
                .authorName("John Doe")
                .build();

        when(bookService.getBook(1)).thenReturn(bookResponse);

        mockMvc.perform(get("/book/getBook")
                        .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Example Book"))
                .andExpect(jsonPath("$.publicationYear").value(2022))
                .andExpect(jsonPath("$.authorName").value("John Doe"));
    }
}
