package com.lms.LibraryManagementSystem.Repository;

import com.lms.LibraryManagementSystem.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRespository extends JpaRepository<Transaction,Integer> {
}
