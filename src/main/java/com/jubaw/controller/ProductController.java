package com.jubaw.controller;

import com.jubaw.domain.Customer;
import com.jubaw.domain.Product;
import com.jubaw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    //CREATE PRODUCT
    //http://localhost:8080/products/add
//    @PostMapping
//    public ResponseEntity<Map<String, String>> createProduct(@Valid @RequestBody Product product) {
//        productService.createProduct(product);
//        Map<String, String> map = new HashMap<>();
//        map.put("message", "Product has been created successfully");
//        map.put("status", "true");
//        return new ResponseEntity<>(map, HttpStatus.CREATED);
//    }

    @PostMapping("/add")
    public ResponseEntity<String> saveProduct(@Valid @RequestBody Product product) {
        productService.saveProduct(product);
        return new ResponseEntity<>("Product has been saved successfully", HttpStatus.CREATED); //200
    }

    //GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getProductList();
        return ResponseEntity.ok(products);
    }

    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductyById(id);

        return  ResponseEntity.ok(product);

    }

    //











}
