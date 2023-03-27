package com.lms.LibraryManagementSystem.Service;

import com.lms.LibraryManagementSystem.DTO.AuthorBooksResponse;
import com.lms.LibraryManagementSystem.DTO.AuthorRequest;
import com.lms.LibraryManagementSystem.Entity.Author;
import com.lms.LibraryManagementSystem.Entity.Book;
import com.lms.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService  {
    @Autowired
    AuthorRepository authorRepository;
    public void addAuthor(AuthorRequest authorRequest)
    {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setMobile(authorRequest.getMobile());
        author.setEmail(authorRequest.getEmail());
        author.setAge(authorRequest.getAge());
        author.setBooks(new ArrayList<>());

        authorRepository.save(author);
    }

    public List<AuthorBooksResponse> getBooksByAuthor(int id) throws Exception {
        Author author;

        try{
            author = authorRepository.findById(id).get();
        }catch (Exception e){
            throw  new Exception("Author not found");
        }

        List<Book> books = author.getBooks();
        List<AuthorBooksResponse> authorBooksResponseList = new ArrayList<>();
        for(Book book:books){
            AuthorBooksResponse authorBooksResponse = new AuthorBooksResponse();
            authorBooksResponse.setId(book.getId());
            authorBooksResponse.setTitle(book.getTitle());
            authorBooksResponse.setPrice(book.getPrice());

            authorBooksResponseList.add(authorBooksResponse);
        }

        return authorBooksResponseList;
    }
}
