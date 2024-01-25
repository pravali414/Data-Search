package com.datasearch.controller;
import com.datasearch.model.Customers;
import com.datasearch.model.Student;
import com.datasearch.repository.CustomersRepository;
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
                    .save(new Customers(customer.getId(), customer.getCustomer_Id(), customer.getFirst_Name(), null, null, 0, 0, new Timestamp(new Date().getTime())));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/customerbyid/{id}")
    public ResponseEntity<Customers> updateCustomers(@PathVariable("id") long id, @RequestBody Customers updatedcustomers) {
        Optional<Customers> customerData = customersRepository.findById(id);
        if (customerData.isPresent()) {
            Customers existingcustomers = customerData.get();
            existingcustomers.setId(updatedcustomers.getId());
            existingcustomers.setCustomer_Id(updatedcustomers.getCustomer_Id());
            if (updatedcustomers.getStore_Id() != null) {
                existingcustomers.setStore_Id(updatedcustomers.getStore_Id());
            }
            if (updatedcustomers.getFirst_Name() != null) {
                existingcustomers.setFirst_Name(updatedcustomers.getFirst_Name());
            }
                existingcustomers.setEmail(updatedcustomers.getEmail());
                existingcustomers.setAddress_Id(updatedcustomers.getAddress_Id());
                existingcustomers.setActive(updatedcustomers.getActive());
                existingcustomers.setCreate_Date(updatedcustomers.getCreate_Date());

                Customers updatedCustomers = customersRepository.save(existingcustomers);
                logger.info("Customers updated: " + updatedCustomers);

                return new ResponseEntity<>(updatedCustomers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomers(@PathVariable("id") long id) {

        try {
            Optional<Customers> customersOptional = customersRepository.findById(id);
            if (customersOptional.isPresent()) {
                Customers customersToDelete = customersOptional.get();
                customersRepository.delete(customersToDelete);
                logger.info("Customer deleted with id: " + id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            logger.error("Error deleting customer with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}






