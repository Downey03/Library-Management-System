package com.lms.LibraryManagementSystem.Service;

import com.lms.LibraryManagementSystem.DTO.*;
import com.lms.LibraryManagementSystem.Entity.Author;
import com.lms.LibraryManagementSystem.Entity.Book;
import com.lms.LibraryManagementSystem.Entity.Transaction;
import com.lms.LibraryManagementSystem.Enum.BookGenre;
import com.lms.LibraryManagementSystem.Exception.AuthorNotFoundException;
import com.lms.LibraryManagementSystem.Repository.AuthorRepository;
import com.lms.LibraryManagementSystem.Repository.BookRepository;
import com.lms.LibraryManagementSystem.Repository.TransactionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    TransactionRespository transactionalRepository;
    public void addBook(BookRequest bookRequest) throws Exception {
        Author author = null;

        try{
            author = authorRepository.findById(bookRequest.getAuthorId()).get();
        }catch (Exception e){
            throw new Exception("Author not found");
        }

        List<Book> booksByAuthor = author.getBooks();

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(bookRequest.getTitle());
        book.setPrice(bookRequest.getPrice());
        book.setBookGenre(bookRequest.getGenre());
        book.setTransactions(new ArrayList<>());

        booksByAuthor.add(book);
        authorRepository.save(author);
    }

    public BookUpdatePriceResponse updatePrice(BookUpdatePriceRequest bookUpdatePriceRequest) throws Exception {
        Book book ;

        try {
            book = bookRepository.findById(bookUpdatePriceRequest.getId()).get();
        }catch (Exception e){
            throw new Exception("Book not Found");
        }

        book.setPrice(bookUpdatePriceRequest.getPrice());
        bookRepository.save(book);

        BookUpdatePriceResponse bookUpdatePriceResponse = new BookUpdatePriceResponse();
        bookUpdatePriceResponse.setTitle(book.getTitle());
        bookUpdatePriceResponse.setPrice(book.getPrice());

        return bookUpdatePriceResponse;
    }

    public void deleteBook(int id) throws Exception {
        Book book;

        try {
            book = bookRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception("Book Not Found");
        }

        List<Transaction> transactions = book.getTransactions();

        for(Transaction transaction : transactions){
            transaction.setBook(null);
            transactionalRepository.save(transaction);
        }

        bookRepository.delete(book);

    }

    public Book getBookById(int id){
        Book book;

        book = bookRepository.findById(id).orElseThrow();

        return book;
    }

    public BooksByAuthorResponse getBooksByAuthor(int id){
        Author author;

        author = authorRepository.findById(id).orElseThrow();

        BooksByAuthorResponse booksByAuthorResponse = new BooksByAuthorResponse();
        booksByAuthorResponse.setName(author.getName());
        booksByAuthorResponse.setBooks(author.getBooks());

        return booksByAuthorResponse;
    }

    public List<BookGetPriceResponse> getBooksLessThan(int price){
        List<Book> bookList;

        bookList = bookRepository.findAll();

        if(bookList.size() == 0) throw new RuntimeException();
        List<BookGetPriceResponse> bookGetPriceResponse = new ArrayList<>();

        for(Book book:bookList){
            if(book.getPrice() > price) continue;
            BookGetPriceResponse newBook = new BookGetPriceResponse();
            newBook.setId(book.getId());
            newBook.setTitle(book.getTitle());
            newBook.setPrice(book.getPrice());
            bookGetPriceResponse.add(newBook);
        }

        return bookGetPriceResponse;
    }

    public List<BookGetPriceResponse> getBooksGreaterThan(int price){
        List<Book> bookList;

        bookList = bookRepository.findAll();

        if(bookList.size() == 0) throw new RuntimeException();
        List<BookGetPriceResponse> bookGetPriceResponse = new ArrayList<>();

        for(Book book:bookList){
            if(book.getPrice() < price) continue;
            BookGetPriceResponse newBook = new BookGetPriceResponse();
            newBook.setId(book.getId());
            newBook.setTitle(book.getTitle());
            newBook.setPrice(book.getPrice());
            bookGetPriceResponse.add(newBook);
        }

        return bookGetPriceResponse;
    }

    public List<BooksByGenreResonse> getBooksByGenre(BookGenre genre){
        List<Book> bookList;

        bookList = bookRepository.findByBookGenre(genre);
        if(bookList.size() == 0) throw new RuntimeException();

        List<BooksByGenreResonse> booksByGenreResonses = new ArrayList<>();
        for(Book book : bookList){
            BooksByGenreResonse booksByGenreResonse = new BooksByGenreResonse();
            booksByGenreResonse.setId(book.getId());
            booksByGenreResonse.setTitle(book.getTitle());
            booksByGenreResonse.setPrice(book.getPrice());
            booksByGenreResonse.setAuthor(book.getAuthor().getName());

            booksByGenreResonses.add(booksByGenreResonse);
        }

        return booksByGenreResonses;
    }
}
