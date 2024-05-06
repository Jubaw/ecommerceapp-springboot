package com.jubaw.service;

import com.jubaw.domain.Customer;
import com.jubaw.exceptions.ConflictException;
import com.jubaw.exceptions.ResourceNotFound;
import com.jubaw.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //CREATE NEW CUSTOMER
    public void createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ConflictException("Customer with this email already exists:  ");
        }
        customerRepository.save(customer);
    }

    //GET CUSTOMER BY ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFound("No customer found for given ID " + id));
    }

    //GET ALL CUSTOMERS
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    //DELETE CUSTOMER BY ID
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.deleteById(id);
    }

    //UPDATE CUSTOMER BY ID
    public Customer updateCustomer(Long id, Customer newCustomerData) {

        Customer toBeUpdatedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        toBeUpdatedCustomer.setName(newCustomerData.getName());
        toBeUpdatedCustomer.setLastName(newCustomerData.getLastName());
        toBeUpdatedCustomer.setPhone(newCustomerData.getPhone());
        toBeUpdatedCustomer.setEmail(newCustomerData.getEmail());


        //METHOD TO CHECK IF THE EMAIL IS ALREADY IN THE SYSTERM
        String newEmail = newCustomerData.getEmail();
        Customer existingCustomer = customerRepository.findByEmail(newEmail);

        if (existingCustomer != null && !existingCustomer.getId().equals(toBeUpdatedCustomer.getId())) {
            throw new ConflictException("Email already exists");
        }

        toBeUpdatedCustomer.setName(newCustomerData.getName());
        return customerRepository.save(toBeUpdatedCustomer);
    }

    //GET CUSTOMER BY NAME
    public Customer getCustomerByName(String name) {
        if (name == null) {
            throw new NullPointerException("No customer found with given name: " + name);
        }
        return customerRepository.findByName(name);
    }

    //GET ALL CUSTOMERS BY PAGE
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    //
    public Customer getCustomerByFullName(String name, String lastName) {
        Customer customer = customerRepository.findByFullName(name,lastName);
        if (customer == null) {
            throw new ResourceNotFound("Customer you are looking for is not found.");
        }
        return customer;

    }
}
