package com.datasearch.controller;
import com.datasearch.model.Student;
import com.datasearch.repository.CustomersRepository;
import com.datasearch.repository.StudentRepository;
import com.datasearch.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudent(@RequestParam(required = false) String name) {
        logger.info("given input value name is = " + name);
        List<Student> students = new ArrayList<Student>();
        students = studentService.getAllStudent(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/student/save")
    public ResponseEntity<Student> createEmployee(@RequestBody Student student) {
        logger.info("given input student Object is = " + student);
        Student obj = studentService.save(student);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student updatedstudent) {

        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {

            Student existingstudent = studentData.get();
            existingstudent.setId(updatedstudent.getId());
            existingstudent.setName(updatedstudent.getName());
            existingstudent.setMarks(updatedstudent.getMarks());
            existingstudent.setPhNumber(updatedstudent.getPhNumber());
            Student UpdatedStudent = studentRepository.save(existingstudent);
            logger.info("Employee updated : " + UpdatedStudent);
            return new ResponseEntity<>(UpdatedStudent, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {

        try {
            Optional<Student> studentOptional = studentRepository.findById(id);
            if (studentOptional.isPresent()) {
                Student studentToDelete = studentOptional.get();
                studentRepository.delete(studentToDelete);
                logger.info("studentdeleted : " + studentToDelete);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}







