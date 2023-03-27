package com.lms.LibraryManagementSystem.DTO;

import com.lms.LibraryManagementSystem.Entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooksByAuthorResponse {
    private String name;
    private List<Book> books;
}
