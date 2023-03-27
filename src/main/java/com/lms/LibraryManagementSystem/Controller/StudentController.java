package com.lms.LibraryManagementSystem.Controller;

import com.lms.LibraryManagementSystem.DTO.*;
import com.lms.LibraryManagementSystem.Entity.Student;
import com.lms.LibraryManagementSystem.Enum.CardStatus;
import com.lms.LibraryManagementSystem.Enum.StudentDepartment;
import com.lms.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add_student")
    public String addStudent(@RequestBody StudentRequest student){
        studentService.addStudent(student);
        return "Student Added Successfully";
    }

    @DeleteMapping("/delete_student")
    public String deleteStudent(@RequestParam int id){
        try {
            studentService.deleteStudent(id);
        }
        catch (Exception e){
            return "Student Not Found";
        }
        return "Student Deleted Successfully";
    }


    @PutMapping("/update_mobile")
    public String updateMobile(@RequestBody StudentUpdateMobileRequest studentUpdateMobileRequest){
        StudentUpdateMobileResponse studentUpdateMobileResponse;

        try{
            studentUpdateMobileResponse = studentService.updateMobile(studentUpdateMobileRequest);
        }
        catch (Exception e){
            return "Student Not Found";
        }

        return "Mobile changed as "+studentUpdateMobileResponse.getMobile()+" for the student "+studentUpdateMobileResponse.getName();
    }
    @PutMapping("/update_mail")
    public String updateMail(@RequestBody StudentUpdateMailRequest studentUpdateMailRequest){
        StudentUpdateMailResponse studentUpdateMailResponse ;

        try {
            studentUpdateMailResponse = studentService.updateMail(studentUpdateMailRequest);
        }
        catch (Exception e){
            return "Student Not Found";
        }

        return "Mail changed as "+ studentUpdateMailResponse.getMail()+" for the student "+ studentUpdateMailResponse.getName();
    }

    @GetMapping("/find_student_by_id")
    public String getStudentById(@RequestParam int id){
        Student student = null;

        try{
            studentService.getStudentById(id);
        }catch (Exception e){
            return "Please Enter Correct Id";
        }

        return student.toString();
    }


    @GetMapping("/find_student_by_mail")
    public String getStudentByMail(@RequestParam String mail){
        Student student = null;

        try{
            studentService.getStudentByMail(mail);
        }catch (Exception e){
            return "Please Enter Your Mail Correctly";
        }

        return student.toString();
    }

    @GetMapping("/find_students_by_age")
    public List<StudentResponse> getStudentsByAge(@RequestParam int age){
        List<StudentResponse> studentResponseList = new ArrayList<>();

        try{
            studentResponseList = studentService.getStudentsByAge(age);
        }catch (Exception e){

        }

        return studentResponseList;
    }

    @GetMapping("/find_students_by_department")
    public List<StudentResponse> getStudentsByDepartment(@RequestParam StudentDepartment studentDepartment){
        List<StudentResponse> studentResponseList = new ArrayList<>();

        try{
            studentResponseList = studentService.getStudentsByDepartment(studentDepartment);
        }catch (Exception e){

        }

        return studentResponseList;
    }

    @GetMapping("/get_all_students")
    public List<StudentResponse> getAllStudents(){
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentResponseList = studentService.getAllStudents();

        return studentResponseList;
    }

    @GetMapping("/get_card_status")
    public StudentCardStatusResponse getCardStatus(@RequestParam int id){
        StudentCardStatusResponse cardStatusResponse = null;
        try {
            cardStatusResponse = studentService.getCardStatus(id);
        }catch (Exception e){

        }

        return cardStatusResponse;
    }
}
