package com.lms.LibraryManagementSystem.DTO;

import com.lms.LibraryManagementSystem.Enum.StudentDepartment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequest {

    private String name;
    private int age;

    private StudentDepartment department;
    private String mail;
    private String mobile;
}
