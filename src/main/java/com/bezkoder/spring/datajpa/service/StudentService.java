package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Student;

import java.util.List;

public interface StudentService {


    List<Student> getAllStudent(String name);

    Student save(Student student);
}
