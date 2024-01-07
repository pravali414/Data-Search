package com.datasearch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datasearch.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findById(long id);
	List<Employee> findByNameIsLike(String name);
	List<Employee> findBySalary(int salary);
	List<Employee> findByRollIsLike(String roll);
}
