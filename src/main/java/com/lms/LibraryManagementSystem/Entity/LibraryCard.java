package com.lms.LibraryManagementSystem.Entity;

import com.lms.LibraryManagementSystem.Enum.CardStatus;
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
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String validTill;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @OneToOne
    @JoinColumn
    Student student;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();

}
