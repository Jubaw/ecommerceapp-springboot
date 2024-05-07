package com.jubaw.controller;


import com.jubaw.domain.Customer;
import com.jubaw.domain.OrderItem;
import com.jubaw.repository.OrderItemRepository;
import com.jubaw.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/orders")
public class OrderItemController {


    @Autowired
    private OrderItemService orderItemService;

    //
//    @PostMapping
//    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderItem orderItem) {
//        orderItemService.createOrder(orderItem);
//        return new ResponseEntity<>("Order has been created successfully", HttpStatus.CREATED);
//    }
    //CREATE CUSTOMER
    //http://localhost:8080/customers + POST
    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(@Valid @RequestBody OrderItem orderItem) {
        orderItemService.createOrder(orderItem);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Order has been created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

}
