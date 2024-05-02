package com.example.OlikAssignment.Repository;

import com.example.OlikAssignment.Models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Integer> {
}
