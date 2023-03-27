package com.lms.LibraryManagementSystem.Repository;

import com.lms.LibraryManagementSystem.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer> {
}
