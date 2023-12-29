package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    List<Student> findByNameIsLike(String name);
    List<Student> findByNameIsStartingWith(String name);

}
