package com.jubaw.controller;

import com.jubaw.domain.Customer;
import com.jubaw.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    //CREATE CUSTOMER
    //http://localhost:8080/customers + POST
    @PostMapping
    public ResponseEntity<Map<String, String>> createCustomer(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Customer has been created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    //GET ALL CUSTOMERS
    //http://localhost:8080/customers + GET
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getAll();
        return ResponseEntity.ok(customerList);
    }

    //GET CUSTOMER BY ID
    //http://localhost:8080/customers/1 + GET
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer found = customerService.getCustomerById(id);
        return new ResponseEntity<>(found,HttpStatus.OK);
    }

    //DELETE CUSTOMER BY ID
    @DeleteMapping("/custom")
    public ResponseEntity<String> deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer has been deleted successfully");
    }
    //TODO:Watch-Out here if it gives error on deletion.

    //GET CUSTOMER BY NAME
    @GetMapping("/query")
    public ResponseEntity<Customer> getCustomerByName(@RequestParam("name") String name) {
        Customer customerFound = customerService.getCustomerByName(name);
        return ResponseEntity.ok(customerFound);
    }
    //UPDATE CUSTOMER WITH ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        String message = "Customer is updated successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}

//TODO:
//6-tüm customerları page page gösterme -> http://localhost:8080/customers/page?page=1&size=2
//&sort=id&direction=ASC


