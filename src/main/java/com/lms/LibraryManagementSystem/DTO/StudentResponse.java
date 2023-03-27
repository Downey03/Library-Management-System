package com.lms.LibraryManagementSystem.DTO;

import com.lms.LibraryManagementSystem.Enum.StudentDepartment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponse {
    private int id;
    private String name;
    private int age;

    private StudentDepartment department;
    private String mobile;
    private String email;
}
