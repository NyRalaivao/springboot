package com.example.query.controller;

import com.example.query.exception.BadRequestException;
import com.example.query.model.Student;
import com.example.query.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // =========================
    // POST /students
    // =========================
    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> students) {

        try {
            List<Student> result = service.addStudents(students);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result);

        } catch (BadRequestException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }

    // =========================
    // GET /students
    // =========================
    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        try {

            if (accept == null) {
                throw new BadRequestException("Header Accept manquant");
            }

            List<Student> students = service.getStudents();

            if (accept.equals("application/json")) {
                return ResponseEntity.ok(students);
            }

            if (accept.equals("text/plain")) {

                StringBuilder result = new StringBuilder();
                for (Student s : students) {
                    result.append(s.getFirstName())
                            .append(" ")
                            .append(s.getLastName())
                            .append("\n");
                }

                return ResponseEntity
                        .ok()
                        .header("Content-Type", "text/plain")
                        .body(result.toString());
            }

            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body("Format non supporté");

        } catch (BadRequestException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }
}