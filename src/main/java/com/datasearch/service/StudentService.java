package com.datasearch.service;

import com.datasearch.model.Student;

import java.util.List;

public interface StudentService {


    List<Student> getAllStudent(String name);

    Student save(Student student);
}
