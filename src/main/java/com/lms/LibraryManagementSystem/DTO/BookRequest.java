package com.lms.LibraryManagementSystem.DTO;

import com.lms.LibraryManagementSystem.Enum.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequest {
    private String title;
    private int price;

    private BookGenre genre;

    private int authorId;
}
