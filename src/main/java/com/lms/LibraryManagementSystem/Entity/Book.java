package com.lms.LibraryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.LibraryManagementSystem.Enum.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Enumerated(EnumType.STRING)
    private BookGenre bookGenre;

    private Boolean isIssued;
    private int price;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Transaction> transactions = new ArrayList<>();
}
