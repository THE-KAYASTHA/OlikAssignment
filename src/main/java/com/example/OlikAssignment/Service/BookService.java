package com.example.OlikAssignment.Service;

import com.example.OlikAssignment.DTOs.RequestDtos.BookRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.Models.Author;
import com.example.OlikAssignment.Models.Book;
import com.example.OlikAssignment.Repository.AuthorRepository;
import com.example.OlikAssignment.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

   @Autowired
   private BookRepository bookRepository;

   @Autowired
   private AuthorRepository authorRepository;

    @Autowired
    private IsbnGenerator isbnGenerator;


    public String generateIsbn() {
        return isbnGenerator.generateIsbn();
    }

    public String addBook(BookRequest bookRequest) throws  Exception{

        Book book=bookRepository.findByTitle(bookRequest.getTitle());
        if(book!=null){
            throw new Exception("Book already Exist");
        }

        String ISBN=generateIsbn();
        book=Book.builder().isBookAvailable(true)

                .title(bookRequest.getTitle())
                .publicationYear(bookRequest.getPublicationYear())
                .ISBN(ISBN)
                 .build();


        Author author=authorRepository.findByAuthorName(bookRequest.getAuthorName());


        book.setAuthor(author);

        author.getBookList().add(book);
        bookRepository.save(book);
        authorRepository.save(author);

        return "Book has been added";

    }

    public BookResponse getBook(Integer bookId)throws Exception {

        Optional<Book> optionalBook=bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new Exception("BookId is not correct");
        }

        Author author=optionalBook.get().getAuthor();
        BookResponse book=BookResponse.builder()
                .bookId(bookId)
                .authorName(author.getAuthorName())
                .title(optionalBook.get().getTitle())
                .isBookAvailable(optionalBook.get().isBookAvailable())
                .publicationYear(optionalBook.get().getPublicationYear()).build();


        return book;




    }
}
