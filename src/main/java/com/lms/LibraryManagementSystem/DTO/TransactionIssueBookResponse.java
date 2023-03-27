package com.lms.LibraryManagementSystem.DTO;

import com.lms.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionIssueBookResponse {
    private String title;
    private String transactionId;
    private TransactionStatus transactionStatus;
}
