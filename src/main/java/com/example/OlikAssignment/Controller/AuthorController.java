package com.example.OlikAssignment.Controller;


import com.example.OlikAssignment.DTOs.RequestDtos.AuthorRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping("/addAuthor")
    public ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest){

        try{

            String ans=authorService.addAuthor(authorRequest);
            return new ResponseEntity(ans,HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }




    }

    @GetMapping("/getBooksByAuthorName")
    public ResponseEntity getBooksByAuthorName(@RequestParam String authorName){

        try{
            List<BookResponse> bookList=authorService.getBooksByAuthorName(authorName);
            return new ResponseEntity(bookList,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteAuthor")
    public ResponseEntity deleteAuthor(@RequestParam String authorName){

        try{
            String ans=authorService.deleteAuthor(authorName);
            return new ResponseEntity(ans,HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }



}
