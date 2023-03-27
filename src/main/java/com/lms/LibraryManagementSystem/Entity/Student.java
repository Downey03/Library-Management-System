package com.lms.LibraryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.LibraryManagementSystem.Enum.StudentDepartment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mobile;
    @Column(unique = true)
    private String email;
    private int age;
    @Enumerated(EnumType.STRING)
    private StudentDepartment studentDepartment;
    @JsonIgnore
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    LibraryCard card;
}
