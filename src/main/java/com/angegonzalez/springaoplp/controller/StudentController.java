package com.angegonzalez.springaoplp.controller;

import java.util.List;
import java.util.Map;

import com.angegonzalez.springaoplp.exception.ResourceNotFoundException;
import com.angegonzalez.springaoplp.model.Student;
import com.angegonzalez.springaoplp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentService StudentService;

    @GetMapping("/students")
    public List <Student> getAllStudents() {
        return StudentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity < Student > getStudentById(@PathVariable(value = "id") Long StudentId)
            throws ResourceNotFoundException {
        Student Student = StudentService.getStudentById(StudentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found for this id :: " + StudentId));
        return ResponseEntity.ok().body(Student);
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student Student) {
        return StudentService.createStudent(Student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity < Student > updateStudent(@PathVariable(value = "id") Long StudentId,
                                                      @Valid @RequestBody Student StudentDetails) throws ResourceNotFoundException {
        Student updatedStudent = StudentService.updateStudent(StudentId, StudentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public Map < String, Boolean > deleteStudent(@PathVariable(value = "id") Long StudentId)
            throws ResourceNotFoundException {
        return StudentService.deleteStudent(StudentId);
    }
}