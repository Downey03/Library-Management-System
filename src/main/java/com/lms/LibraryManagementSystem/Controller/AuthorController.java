package com.lms.LibraryManagementSystem.Controller;

import com.lms.LibraryManagementSystem.DTO.AuthorBooksResponse;
import com.lms.LibraryManagementSystem.DTO.AuthorRequest;
import com.lms.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add_author")
    public String addAuthor(@RequestBody AuthorRequest authorRequest){
        authorService.addAuthor(authorRequest);
        return "Author added";
    }

    @GetMapping("/get_books_by_author")
    public List<AuthorBooksResponse> getBooksByAuthor(@RequestParam int id) throws Exception {
        List<AuthorBooksResponse> authorBooksResponseList;
        authorBooksResponseList = authorService.getBooksByAuthor(id);
        return  authorBooksResponseList;
    }
}
