package com.lms.LibraryManagementSystem.Service;

import com.lms.LibraryManagementSystem.DTO.*;
import com.lms.LibraryManagementSystem.Entity.LibraryCard;
import com.lms.LibraryManagementSystem.Entity.Student;
import com.lms.LibraryManagementSystem.Enum.CardStatus;
import com.lms.LibraryManagementSystem.Enum.StudentDepartment;
import com.lms.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.lms.LibraryManagementSystem.Enum.CardStatus.ACTIVE;


@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(StudentRequest studentRequest){
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setStudentDepartment(studentRequest.getDepartment());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getMail());
        student.setMobile(studentRequest.getMobile());

        LibraryCard card = new LibraryCard();
        card.setCardStatus(ACTIVE);
        card.setValidTill("03/2/2025");
        card.setStudent(student);

        student.setCard(card);
        studentRepository.save(student);
    }

    public void deleteStudent(int id) throws Exception {
        Student student ;
        try {
            student = studentRepository.findById(id).get();
        }catch (Exception e) {
            throw new Exception("Student Not found");
        }
        studentRepository.deleteById(id);
    }

    public StudentUpdateMobileResponse updateMobile(StudentUpdateMobileRequest studentUpdateMobileRequest) throws Exception {
        Student student;

        try {
            student = studentRepository.findById(studentUpdateMobileRequest.getId()).get();
        }catch (Exception e) {
            throw new Exception("Student Not found");
        }

        student.setMobile(studentUpdateMobileRequest.getMobile());
        studentRepository.save(student);

        StudentUpdateMobileResponse studentUpdateMobileResponse = new StudentUpdateMobileResponse();
        studentUpdateMobileResponse.setMobile(student.getMobile());
        studentUpdateMobileResponse.setMobile(student.getName());

        return studentUpdateMobileResponse;
    }

    public StudentUpdateMailResponse updateMail(StudentUpdateMailRequest studentUpdateMailRequest) throws Exception {
        Student student ;

        try {
            student = studentRepository.findById(studentUpdateMailRequest.getId()).get();
        }catch (Exception e) {
            throw new Exception("Student Not found");
        }

        student.setEmail(studentUpdateMailRequest.getMail());
        studentRepository.save(student);

        StudentUpdateMailResponse studentUpdateMailResponse = new StudentUpdateMailResponse();
        studentUpdateMailResponse.setMail(student.getEmail());
        studentUpdateMailResponse.setName(student.getName());

        return studentUpdateMailResponse;
    }

    public Student getStudentByMail(String mail){
        Student student;

        student = studentRepository.findByMail(mail);
        if(student == null) throw new RuntimeException();

        return student;
    }

    public Student getStudentById(int id) throws Exception {
        Student student;

        try {
            student = studentRepository.findById(id).get();
        }catch (Exception e) {
            throw new Exception("Student Not found");
        }

        return student;
    }

    public List<StudentResponse> getStudentsByAge(int age){
        List<Student> studentList;

        studentList = studentRepository.findAllByAge(age);
        if(studentList.size() == 0) throw new RuntimeException();

        List<StudentResponse> studentResponseList = new ArrayList<>();

        for(Student student :studentList){
            StudentResponse studentResponse = new StudentResponse();

            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());

            studentResponseList.add(studentResponse);
        }

        return studentResponseList;
    }

    public List<StudentResponse> getStudentsByDepartment(StudentDepartment studentDepartment){
        List<Student> studentList;

        studentList = studentRepository.findAllByStudentDepartment(studentDepartment);
        if(studentList.size() == 0) throw new RuntimeException();

        List<StudentResponse> studentResponseList = new ArrayList<>();
        for(Student student:studentList){
            StudentResponse studentResponse = new StudentResponse();

            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());

            studentResponseList.add(studentResponse);
        }

        return studentResponseList;
    }

    public List<StudentResponse> getAllStudents(){
        List<StudentResponse> studentResponseList = new ArrayList<>();
        List<Student> studentList;

        studentList = studentRepository.findAll();

        for(Student student : studentList){
            StudentResponse studentResponse = new StudentResponse();

            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());
            studentResponse.setMobile(student.getMobile());
            studentResponse.setEmail(student.getEmail());
            studentResponse.setAge(student.getAge());
            studentResponse.setDepartment(student.getStudentDepartment());

            studentResponseList.add(studentResponse);
        }
        return studentResponseList;
    }

    public StudentCardStatusResponse getCardStatus(int id) throws Exception {
        Student student ;
        try {
            student = studentRepository.findById(id).get();
        }catch (Exception e) {
            throw new Exception("Student Not found");
        }

        LibraryCard card = student.getCard();
        StudentCardStatusResponse studentCardStatusResponse = new StudentCardStatusResponse();

        studentCardStatusResponse.setStatus(card.getCardStatus());
        studentCardStatusResponse.setValidTill(card.getValidTill());

        return studentCardStatusResponse;
    }
}
