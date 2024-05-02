package com.example.OlikAssignment.Repository;

import com.example.OlikAssignment.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    public Book findByISBN(String ISBN);
    public Book findByTitle(String bookName);

}
