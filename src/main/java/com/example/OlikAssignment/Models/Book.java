package com.example.OlikAssignment.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Books")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    private String title;

    @Column(unique = true)
    private String ISBN;

    private boolean isBookAvailable;
    private int publicationYear;

        @JoinColumn
        @ManyToOne
        private Author author;


        @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
        private List<Rental> rentalList=new ArrayList<>();
}
