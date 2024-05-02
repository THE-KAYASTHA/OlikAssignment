package com.example.OlikAssignment.Controller;


import com.example.OlikAssignment.DTOs.RequestDtos.RentRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.DTOs.ResponseDtos.RentalResponse;
import com.example.OlikAssignment.Models.Rental;
import com.example.OlikAssignment.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;


    @PostMapping("/bookRented")
    public String bookRented(@RequestBody RentRequest rentRequest){

        String ans=rentalService.bookRented(rentRequest);

        return ans;



    }

    @PutMapping("/bookReturn")
    public String bookReturn(@RequestParam Integer rentalId,
                             @RequestParam Integer bookId){

        String ans=rentalService.bookReturn(rentalId,bookId);

        return ans;
    }


    @GetMapping("/booksAvailableForRent")
    public List<BookResponse> bookAvailableForRent(){

        List<BookResponse> bookList=rentalService.bookAvailableForRent();

        return bookList;


    }


    @GetMapping("/booksRented")
    public List<BookResponse> booksRented(){

        List<BookResponse> bookList=rentalService.booksRented();

        return bookList;


    }


    @GetMapping("/rentalsOverDue")
    public List<RentalResponse> booksOverDue(){

        List<RentalResponse> rentalList=rentalService.rentalOverDue();

        return rentalList;
    }



}
