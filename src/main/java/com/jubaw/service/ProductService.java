package com.jubaw.service;

import com.jubaw.domain.Product;
import com.jubaw.exceptions.ConflictException;
import com.jubaw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    //CREATE NEW PRODUCT
    public void saveProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ConflictException("The product you want to add already exists.");
        }
        productRepository.save(product);
    }








}
