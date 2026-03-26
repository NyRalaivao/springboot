package com.example.query.validator;

import com.example.query.exception.BadRequestException;
import com.example.query.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidator {

    public void validate(List<Student> students) {

        for (Student s : students) {

            if (s.getReference() == null || s.getReference().isBlank()) {
                throw new BadRequestException("Reference is required");
            }

            if (s.getFirstName() == null || s.getFirstName().isBlank()) {
                throw new BadRequestException("FirstName is required");
            }

            if (s.getLastName() == null || s.getLastName().isBlank()) {
                throw new BadRequestException("LastName is required");
            }
        }
    }
}