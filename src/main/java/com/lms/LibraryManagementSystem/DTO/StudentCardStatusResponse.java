package com.lms.LibraryManagementSystem.DTO;

import com.lms.LibraryManagementSystem.Enum.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentCardStatusResponse {
    private CardStatus status;
    private String validTill;
}
