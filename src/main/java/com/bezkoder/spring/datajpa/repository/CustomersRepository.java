package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    Optional<Customers> findById(Long customers_Id);
}
