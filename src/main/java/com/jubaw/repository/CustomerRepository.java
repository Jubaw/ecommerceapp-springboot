package com.jubaw.repository;

import com.jubaw.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);


    Customer findByName(String name);

    Customer findByEmail(String email);
}
