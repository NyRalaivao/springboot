package com.example.query.service;

import com.example.query.model.Student;
import com.example.query.validator.StudentValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private final StudentValidator validator;

    public StudentService(StudentValidator validator) {
        this.validator = validator;
    }

    public List<Student> addStudents(List<Student> newStudents) {

        // Validation
        validator.validate(newStudents);

        // Sauvegarde
        students.addAll(newStudents);

        return students;
    }

    public List<Student> getStudents() {
        return students;
    }
}