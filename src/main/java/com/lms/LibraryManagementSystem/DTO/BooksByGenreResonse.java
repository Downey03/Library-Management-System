package com.lms.LibraryManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooksByGenreResonse {
    private int id;
    private String title;
    private int price;
    private String author;
}
