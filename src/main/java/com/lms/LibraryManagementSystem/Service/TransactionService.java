package com.lms.LibraryManagementSystem.Service;

import com.lms.LibraryManagementSystem.DTO.TransactionIssueBookRequest;
import com.lms.LibraryManagementSystem.DTO.TransactionIssueBookResponse;
import com.lms.LibraryManagementSystem.Entity.Book;
import com.lms.LibraryManagementSystem.Entity.LibraryCard;
import com.lms.LibraryManagementSystem.Entity.Transaction;
import com.lms.LibraryManagementSystem.Enum.CardStatus;
import com.lms.LibraryManagementSystem.Enum.TransactionStatus;
import com.lms.LibraryManagementSystem.Repository.BookRepository;
import com.lms.LibraryManagementSystem.Repository.LibraryCardRepository;
import com.lms.LibraryManagementSystem.Repository.TransactionRespository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRespository transactionRespository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryCardRepository libraryCardRepository;

    public TransactionIssueBookResponse issueBook(TransactionIssueBookRequest transactionIssueBookRequest) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);
        transaction.setTransactionData(new Date());

        transaction.setStatus(TransactionStatus.FAILED);
        Book book ;
        try{
            book = bookRepository.findById(transactionIssueBookRequest.getBookId()).get();
        }catch (Exception e){
            transactionRespository.save(transaction);
            throw new Exception("Book Not Found");
        }

        transaction.setBook(book);
        LibraryCard card;

        try {
            card = libraryCardRepository.findById(transactionIssueBookRequest.getCardId()).get();
        }catch (Exception e){
            transactionRespository.save(transaction);
            throw new Exception("Card Not Found");
        }
        transaction.setCard(card);

        if(book.getIsIssued()) {
            transactionRespository.save(transaction);
            throw new Exception("Book Not Available");
        }
        if(card.getCardStatus() == CardStatus.BLOCKED) {
            transactionRespository.save(transaction);
            throw new Exception("Card Is Blocked, Please Contact Admin");
        }
        if(card.getCardStatus() == CardStatus.EXPIRED) {
            transactionRespository.save(transaction);
            throw new Exception("Card Is Expired, Please Contact Admin");
        }
        if(card.getCardStatus() == CardStatus.INACTIVE) {
            transactionRespository.save(transaction);
            throw new Exception("Card Is Inactive, Please Activate your Card");
        }


        transaction.setStatus(TransactionStatus.SUCCESS);

        book.setIsIssued(true);
        card.getTransactions().add(transaction);
        book.getTransactions().add(transaction);
        libraryCardRepository.save(card);

        TransactionIssueBookResponse transactionIssueBookResponse = new TransactionIssueBookResponse();

        transactionIssueBookResponse.setTransactionId(transaction.getTransactionNumber());
        transactionIssueBookResponse.setTitle(book.getTitle());
        transactionIssueBookResponse.setTransactionStatus(transaction.getStatus());

        return  transactionIssueBookResponse;
    }
}
