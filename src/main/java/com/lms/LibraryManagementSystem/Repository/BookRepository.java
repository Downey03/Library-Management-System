package com.lms.LibraryManagementSystem.Repository;

import com.lms.LibraryManagementSystem.Entity.Book;
import com.lms.LibraryManagementSystem.Enum.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByBookGenre(BookGenre genre);
}
