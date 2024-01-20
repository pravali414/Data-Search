package com.datasearch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.datasearch.model.Employee;
import com.datasearch.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/data-search")
public class EmployeeController {
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/allemployees")
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(required = false) String name) {
		logger.info("given input value as name is = " + name);


		try {
			List<Employee> employees = new ArrayList<Employee>();

			if (name == null)
				employeeRepository.findAll().forEach(employees::add);
			else
				employeeRepository.findByNameIsLike(name).forEach(employees::add);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employeebyid/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
		Optional<Employee> employeeData = employeeRepository.findById(id);
		logger.info("Employee : " + id);

		if (employeeData.isPresent()) {
			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		logger.info("Employee : " + employee);
		try {
			Employee _employee = employeeRepository
					.save(employee);
			return new ResponseEntity<>(_employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee updatedemployee) {

		Optional<Employee> employeeData = employeeRepository.findById(id);

		if (employeeData.isPresent()) {

			Employee existingemployee = employeeData.get();
			existingemployee.setId(updatedemployee.getId());
			existingemployee.setName(updatedemployee.getName());
			existingemployee.setSalary(updatedemployee.getSalary());
			existingemployee.setRoll(updatedemployee.getRoll());
			Employee UpdatedEmployee=employeeRepository.save(existingemployee);
			logger.info("Employee updated : " + UpdatedEmployee);
			return new ResponseEntity<>(UpdatedEmployee, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {

		try {
			Optional<Employee> employeeOptional = employeeRepository.findById(id);
			if (employeeOptional.isPresent()) {
				Employee employeeToDelete = employeeOptional.get();
				employeeRepository.delete(employeeToDelete);
				logger.info("Employeedeleted : " + employeeToDelete);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		try {
			employeeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/employeebysalary/{Salary}")
	public ResponseEntity<List<Employee>> findBySalary(@PathVariable("Salary") int salary) {
		try {
			List<Employee> employees = employeeRepository.findBySalary(salary);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/employeebyroll/{roll}")
	public ResponseEntity<List<Employee>>getEmployeeByRoll(@PathVariable("roll") String roll) {
		logger.info("given employee roll : " + roll);
		try {
			List<Employee> employees = new ArrayList<Employee>();

			if (roll == null)
				employeeRepository.findAll().forEach(employees::add);
			else
				employeeRepository.findByRollIsLike(roll).forEach(employees::add);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	}


