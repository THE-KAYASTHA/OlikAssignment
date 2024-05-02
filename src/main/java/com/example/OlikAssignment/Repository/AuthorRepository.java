package com.example.OlikAssignment.Repository;

import com.example.OlikAssignment.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    public Author findByAuthorName(String authorName);

}
