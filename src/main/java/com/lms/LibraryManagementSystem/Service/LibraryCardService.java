package com.lms.LibraryManagementSystem.Service;

import com.lms.LibraryManagementSystem.Entity.LibraryCard;
import com.lms.LibraryManagementSystem.Entity.Student;
import com.lms.LibraryManagementSystem.Enum.CardStatus;
import com.lms.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.lms.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryCardService {
    @Autowired
    LibraryCardRepository libraryCardRepository;


    public Student getStudentDetails(int id) throws Exception {

        LibraryCard card;
        try {
            card = libraryCardRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception("Card Not Found");
        }

        Student student = card.getStudent();

        return student;
    }

    public String getValidity(int id) throws Exception {
        LibraryCard card ;
        try {
            card = libraryCardRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception("Card Not Found");
        }

        return card.getValidTill();
    }

    public CardStatus getStatus(int id) throws Exception {
        LibraryCard card ;
        try {
            card = libraryCardRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception("Card Not Found");
        }

        return card.getCardStatus();
    }
}
