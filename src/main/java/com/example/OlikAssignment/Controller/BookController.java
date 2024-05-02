package com.example.OlikAssignment.Controller;


import com.example.OlikAssignment.DTOs.RequestDtos.BookRequest;
import com.example.OlikAssignment.DTOs.ResponseDtos.BookResponse;
import com.example.OlikAssignment.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

        @Autowired
        private BookService bookService;

        @PostMapping("/addBook")
        public ResponseEntity addBook(@RequestBody BookRequest bookRequest){

          try{
                  String ans=bookService.addBook(bookRequest);

                return new ResponseEntity(ans, HttpStatus.OK);
          }
          catch(Exception e){
                  return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
          }

        }

        @GetMapping("/getBook")
        public ResponseEntity getBook(@RequestParam Integer bookId){
           try {
               BookResponse book = bookService.getBook(bookId);
                    return new ResponseEntity(book,HttpStatus.OK);

           }
           catch(Exception e){
               return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
           }
        }


}
