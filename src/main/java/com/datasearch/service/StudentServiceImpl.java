package com.datasearch.service;

import com.datasearch.model.Student;
import com.datasearch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent(String name) {
        List<Student> students = new ArrayList<>();
        if (name == null)
            studentRepository.findAll().forEach(students::add);
        else
            studentRepository.findByNameIsStartingWith(name).forEach(students::add);
        return students;
    }

    @Override
    public Student save(Student student) {
       return studentRepository.save(student);
    }
}
