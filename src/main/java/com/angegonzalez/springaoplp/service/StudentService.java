package com.angegonzalez.springaoplp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.angegonzalez.springaoplp.exception.ResourceNotFoundException;
import com.angegonzalez.springaoplp.model.Student;
import com.angegonzalez.springaoplp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    @Autowired
    private StudentRepository StudentRepository;

    public List <Student> getAllStudents() {
        return StudentRepository.findAll();
    }

    public Optional < Student > getStudentById(Long StudentId)
            throws ResourceNotFoundException {
        return StudentRepository.findById(StudentId);
    }

    public Student createStudent(Student Student) {
        return StudentRepository.save(Student);
    }

    public Student updateStudent(Long StudentId,
                                   Student StudentDetails) throws ResourceNotFoundException {
        Student Student = StudentRepository.findById(StudentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + StudentId));

        Student.setEmailId(StudentDetails.getEmailId());
        Student.setLastName(StudentDetails.getLastName());
        Student.setFirstName(StudentDetails.getFirstName());
        final Student updatedStudent = StudentRepository.save(Student);
        return updatedStudent;
    }

    public Map < String, Boolean > deleteStudent(Long StudentId)
            throws ResourceNotFoundException {
        Student Student = StudentRepository.findById(StudentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found for this id :: " + StudentId));

        StudentRepository.delete(Student);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}