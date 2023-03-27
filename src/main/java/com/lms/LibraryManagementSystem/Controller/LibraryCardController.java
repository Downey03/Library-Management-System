package com.lms.LibraryManagementSystem.Controller;

import com.lms.LibraryManagementSystem.Entity.Student;
import com.lms.LibraryManagementSystem.Enum.CardStatus;
import com.lms.LibraryManagementSystem.Service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class LibraryCardController {
    @Autowired
    LibraryCardService libraryCardService;

    @GetMapping("/get_student")
    public Student getCardStatus(@RequestParam int id){
        Student student = null;
        try {
            student = libraryCardService.getStudentDetails(id);
        }catch (Exception e){

        }

        return student;
    }

    @GetMapping("/get_validity")
    public String  getValidity(@RequestParam int id){
        String validity;
        try {
            validity = libraryCardService.getValidity(id);
        }catch (Exception e){
            return "Card Not Found,Please Check The Id";
        }

        return validity;
    }

    @GetMapping("/get_status")
    public CardStatus getStatus(@RequestParam int id){
        CardStatus status = null;
        try {
            status = libraryCardService.getStatus(id);
        }catch (Exception e){

        }

        return status;
    }
}
