package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Employee;
import com.bezkoder.spring.datajpa.model.Student;
import com.bezkoder.spring.datajpa.repository.StudentRepository;
import com.bezkoder.spring.datajpa.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@CrossOrigin(origins = "http://localhost:8080")
    @RestController
    @RequestMapping("/data-search")
    public class StudentController {
    @Autowired
    private StudentService studentService;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudent(@RequestParam(required = false) String name) {
        logger.info("given input value name is = " + name);
        List<Student> students = new ArrayList<Student>();
        students  = studentService.getAllStudent(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @PostMapping("/student/save")
    public ResponseEntity<Student> createEmployee(@RequestBody Student student) {
        logger.info("given input student Object is = " + student);
        Student obj  = studentService.save(student);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}





