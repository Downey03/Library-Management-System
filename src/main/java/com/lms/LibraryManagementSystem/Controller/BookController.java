package com.lms.LibraryManagementSystem.Controller;

import com.lms.LibraryManagementSystem.DTO.*;
import com.lms.LibraryManagementSystem.Entity.Book;
import com.lms.LibraryManagementSystem.Enum.BookGenre;
import com.lms.LibraryManagementSystem.Service.BookService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add_book")
    public String addBook(@RequestBody BookRequest bookRequest){
        try{
            bookService.addBook(bookRequest);
        }
        catch (Exception e){
            return e.toString();
        }
        return "Book added";
    }

    @PutMapping("/update_price")
    public  String updatePrice(@RequestBody BookUpdatePriceRequest bookUpdatePriceRequest){
        BookUpdatePriceResponse bookUpdatePriceResponse;

        try{
            bookUpdatePriceResponse = bookService.updatePrice(bookUpdatePriceRequest);
        }catch (Exception e){
            return "Book Not Found, Please Check Your Book ID";
        }

        return "The New Price of "+bookUpdatePriceResponse.getTitle()+" is "+bookUpdatePriceResponse.getPrice();
    }

    @DeleteMapping("/delete_book")
    public String deleteBook(@RequestParam int id){
        try{
            bookService.deleteBook(id);
        }catch (Exception e){
            return "Book Not Found, Please Check Your Book ID";
        }

        return "Book Deleted Successfully";
    }

    @GetMapping("/get_book_by_id")
    public String getBookById(@RequestParam int id){
        Book book;
        try{
            book = bookService.getBookById(id);
        }catch (Exception e){
            return e.toString();
        }

        return book.toString();
    }

    @GetMapping("/get_books_by_author")
    public BooksByAuthorResponse getBooksByAuthor(@RequestParam int id){
        BooksByAuthorResponse booksByAuthorResponse = null;
        try{
            booksByAuthorResponse = bookService.getBooksByAuthor(id);
        }catch (Exception e){

        }

        return booksByAuthorResponse;
    }

    @GetMapping("/get_books_less_than")
    public List<BookGetPriceResponse> getBooksLessThan(@RequestParam int price){
        List<BookGetPriceResponse> bookGetPriceResponse = null;

        try {
            bookGetPriceResponse = bookService.getBooksLessThan(price);
        }catch (Exception e){

        }

        return bookGetPriceResponse;
    }

    @GetMapping("/get_books_greater_than")
    public List<BookGetPriceResponse> getBooksGreaterThan(@RequestParam int price){
        List<BookGetPriceResponse> bookGetPriceResponse = null;

        try {
            bookGetPriceResponse = bookService.getBooksGreaterThan(price);
        }catch (Exception e){

        }

        return bookGetPriceResponse;
    }

    @GetMapping("/get_books_by_genre")
    public List<BooksByGenreResonse> getBooksByGenre(@RequestParam BookGenre genre){
        List<BooksByGenreResonse> booksByGenreResonses  = null;

        try {
            booksByGenreResonses = bookService.getBooksByGenre(genre);
        }catch (Exception e){

        }

        return booksByGenreResonses;
    }
}
