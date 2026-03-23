package com.example.query.controller;

import com.example.query.repository.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>();


    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return "Welcome " + name;
    }

    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> newStudents) {

        students.addAll(newStudents);

        StringBuilder result = new StringBuilder();

        for (Student s : students) {
            result.append(s.getFirstName())
                    .append(" ")
                    .append(s.getLastName())
                    .append("\n");
        }

        return result.toString();
    }


    @GetMapping("/students")
    public String getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        StringBuilder result = new StringBuilder();

        for (Student s : students) {
            result.append(s.getFirstName())
                    .append(" ")
                    .append(s.getLastName())
                    .append("\n");
        }

        if (accept != null && accept.equals("application/json")) {
            return "{ \"students\": \"" + result.toString().trim() + "\" }";
        }

        return result.toString();
    }
}