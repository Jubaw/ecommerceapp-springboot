package com.jubaw.repository;

import com.jubaw.domain.Customer;
import com.jubaw.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);

    @Query("select u from Customer u where u.name = :name and u.lastName = :lastName")
    Customer findByFullName(@Param("name") String name, @Param("lastName") String lastName);


    List<Customer> findByName(String name);

    Customer findByEmail(String email);


//    List<Customer> findByNameContaining(String name);

    @Query("SELECT u FROM Customer u WHERE LOWER(u.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Customer> findByNameEndpoint(@Param("name") String name);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.customer.id = :customerId")
    List<OrderItem> findOrderItemsByCustomerId(@Param("customerId") Long customerId);}
