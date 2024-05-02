package com.example.OlikAssignment.Service;

import com.example.OlikAssignment.DTOs.RequestDtos.AuthorRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.Models.Author;
import com.example.OlikAssignment.Models.Book;
import com.example.OlikAssignment.Repository.AuthorRepository;
import com.example.OlikAssignment.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public String addAuthor(AuthorRequest authorRequest)throws Exception {

        Author author=authorRepository.findByAuthorName(authorRequest.getAuthorName());

        if(author!=null){
            throw new Exception("Author Already Exist");
        }

        author=Author.builder().authorName(authorRequest.getAuthorName())
                .biography(authorRequest.getBiography())
                .build();
        author=authorRepository.save(author);

        return "Author has been added with authorId "+author.getAuthorId();
    }

    public List<BookResponse> getBooksByAuthorName(String authorName)throws Exception {

        Author author=authorRepository.findByAuthorName(authorName);

        if(author==null){
            throw new Exception("Author name is not Correct");
        }

        List<Book> bookList=author.getBookList();
        List<BookResponse> bookResponseList=new ArrayList<>();

        for(Book i:bookList){
            BookResponse book=BookResponse.builder()
                    .bookId(i.getBookId())
                    .authorName(author.getAuthorName())
                    .title( i.getTitle())
                    .isBookAvailable(i.isBookAvailable())
                    .publicationYear(i.getPublicationYear()).build();
            bookResponseList.add(book);

        }


        return bookResponseList;

    }

    public String deleteAuthor(String authorName) throws Exception{

        Author author=authorRepository.findByAuthorName(authorName);
        if(author==null){
            throw new Exception("Author does not exist");
        }

        List<Book> bookList=author.getBookList();

        authorRepository.delete(author);

        for(Book i:bookList){
            bookRepository.delete(i);
        }

        return "Author has been deleted";
    }
}
