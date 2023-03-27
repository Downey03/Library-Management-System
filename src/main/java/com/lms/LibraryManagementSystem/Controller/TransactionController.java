package com.lms.LibraryManagementSystem.Controller;

import com.lms.LibraryManagementSystem.DTO.TransactionIssueBookRequest;
import com.lms.LibraryManagementSystem.DTO.TransactionIssueBookResponse;
import com.lms.LibraryManagementSystem.Entity.Book;
import com.lms.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue_book")
    public TransactionIssueBookResponse issueBook(@RequestBody TransactionIssueBookRequest transactionIssueBookRequest) throws Exception {
        return transactionService.issueBook(transactionIssueBookRequest);
    }
}
