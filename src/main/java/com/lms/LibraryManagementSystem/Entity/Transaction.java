package com.lms.LibraryManagementSystem.Entity;

import com.lms.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    private int id;

    private String transactionNumber;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private boolean isIssueOperation;
    @CreationTimestamp
    private Date transactionData;

    @ManyToOne
    @JoinColumn
    Book book;

    @ManyToOne
    @JoinColumn
    LibraryCard card;
}
