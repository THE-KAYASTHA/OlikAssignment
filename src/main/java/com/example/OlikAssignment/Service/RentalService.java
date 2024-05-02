package com.example.OlikAssignment.Service;


import com.example.OlikAssignment.DTOs.RequestDtos.RentRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.DTOs.ResponseDtos.RentalResponse;
import com.example.OlikAssignment.Models.Book;
import com.example.OlikAssignment.Models.Rental;
import com.example.OlikAssignment.Repository.BookRepository;
import com.example.OlikAssignment.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentalRepository rentalRepository;


    public String bookRented(RentRequest rentRequest) {

    Optional<Book> optionalBook = bookRepository.findById(rentRequest.getBookId());
    if(optionalBook.isEmpty()) return "BookId is not correct";


    Book book=optionalBook.get();

    if(book.isBookAvailable()==false) return "Book is not available";

    Rental rental=Rental.builder()
            .rentalName(rentRequest.getRentalName())
            .rentalDate(LocalDate.now())
            .returnDate(LocalDate.now().plusDays(14))
            .book(book).build();

    book.setBookAvailable(false);
    book.getRentalList().add(rental);
   book= bookRepository.save(book);

   rental=rentalRepository.save(rental);

   return "Book ID "+book.getBookId()+" has been rented to "+rentRequest.getRentalName() +" with Rental Id" +rental.getRentalId();

    }

    public String bookReturn(Integer rentalId, Integer bookId) {

        Rental rental=rentalRepository.findById(rentalId).get();
        Book book=bookRepository.findById(bookId).get();

        rental.setReturnDate(LocalDate.now());
        rental.setBookReturned(true);

        book.setBookAvailable(true);

        book=bookRepository.save(book);
        rental=rentalRepository.save(rental);

        return "Book has been returned Successfully";



    }

    public List<BookResponse> bookAvailableForRent() {

    List<Book> bookList=bookRepository.findAll();

    List<BookResponse> bookResponseList=new ArrayList<>();

    for(Book i:bookList){

        if(i.isBookAvailable()){
            BookResponse book=BookResponse.builder()
                    .bookId(i.getBookId())
                    .authorName( i.getAuthor().getAuthorName())
                    .title( i.getTitle())
                    .isBookAvailable(i.isBookAvailable())
                    .publicationYear(i.getPublicationYear()).build();
            bookResponseList.add(book);
        }
    }

    return bookResponseList;



    }

    public List<BookResponse> booksRented() {

    List<Book> bookList=bookRepository.findAll();

    List<BookResponse> bookResponseList=new ArrayList<>();

    for(Book i:bookList){
        if(i.isBookAvailable()==false){
            BookResponse book=BookResponse.builder()
                    .bookId(i.getBookId())
                    .authorName( i.getAuthor().getAuthorName())
                    .title( i.getTitle())
                    .isBookAvailable(i.isBookAvailable())
                    .publicationYear(i.getPublicationYear()).build();
            bookResponseList.add(book);
        }
    }

    return bookResponseList;

    }

    public List<RentalResponse> rentalOverDue() {

    List<Rental> rentalList=rentalRepository.findAll();
    List<RentalResponse> newRentalList=new ArrayList<>();

    for(Rental i:rentalList){

        if(i.getReturnDate().isBefore(LocalDate.now()) && i.isBookReturned()==false){
            RentalResponse rentalResponse=RentalResponse.builder()
                    .rentalId(i.getRentalId())
                    .rentalName(i.getRentalName())
                    .rentalDate(i.getRentalDate())
                    .returnDate(i.getReturnDate())
                    .bookReturned(i.isBookReturned())
                    .bookId(i.getBook().getBookId())
                    .build();

            newRentalList.add(rentalResponse);
        }



    }

    return newRentalList;

    }
}
