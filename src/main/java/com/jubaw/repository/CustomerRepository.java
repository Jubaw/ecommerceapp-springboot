package com.jubaw.repository;

import com.jubaw.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);

    @Query("select u from Customer u where u.name = :name and u.lastName = :lastName")
    Customer findByFullName(@Param("name") String name, @Param("lastName") String lastName);


    Customer findByName(String name);

    Customer findByEmail(String email);



}
