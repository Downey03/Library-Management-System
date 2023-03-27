package com.lms.LibraryManagementSystem.Repository;

import com.lms.LibraryManagementSystem.DTO.StudentResponse;
import com.lms.LibraryManagementSystem.Entity.Student;
import com.lms.LibraryManagementSystem.Enum.StudentDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByMail(String mail);

    List<Student> findAllByAge(int age);

    List<Student> findAllByStudentDepartment(StudentDepartment studentDepartment);
}
