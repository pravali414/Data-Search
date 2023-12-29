package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Student;
import com.bezkoder.spring.datajpa.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.datajpa.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.bezkoder.spring.datajpa.repository.EmployeeRepository;
@CrossOrigin(origins = "http://localhost:8080")
    @RestController
    @RequestMapping("/data-search")
    public class StudentController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    StudentRepository studentRepository;
    @GetMapping("/Students")
    public ResponseEntity<List<Student>> getAllStudent(@RequestParam(required = false) String name) {
        logger.info("given input value name is = " + name);
        List<Student> students = new ArrayList<Student>();

        if (name == null)
           studentRepository.findAll().forEach(students::add);
        else
            studentRepository.findByNameIsStartingWith(name).forEach(students::add);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}





