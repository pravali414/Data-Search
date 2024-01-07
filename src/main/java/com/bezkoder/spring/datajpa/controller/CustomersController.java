package com.bezkoder.spring.datajpa.controller;
import com.bezkoder.spring.datajpa.model.Customers;
import com.bezkoder.spring.datajpa.repository.CustomersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/data-search")
public class CustomersController {
    Logger logger = LoggerFactory.getLogger(CustomersController.class);
    @Autowired
    CustomersRepository customersRepository;


    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable Long customerId) {
        logger.info("Requested customer with ID = " + customerId);

        Optional<Customers> customer = customersRepository.findById(customerId);

        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/customer")
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers customer) {
        logger.info("Customer : " + customer);
        try {
            Customers _employee = customersRepository
                    .save(new Customers(customer.getId(), customer.getCustomer_Id(), customer.getFirst_Name(),null,null,0,0,new Timestamp(new Date().getTime())));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

