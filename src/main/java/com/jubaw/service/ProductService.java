package com.jubaw.service;

import com.jubaw.domain.Product;
import com.jubaw.exceptions.ConflictException;
import com.jubaw.exceptions.ResourceNotFound;
import com.jubaw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    //CREATE NEW PRODUCT
    public void saveProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ConflictException("The product you want to add already exists.");
        }
        productRepository.save(product);
    }


    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product getProductyById(Long id) {

        return productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFound("The product you are looking for is not found."));

    }







}
