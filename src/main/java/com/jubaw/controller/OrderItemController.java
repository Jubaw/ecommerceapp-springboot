package com.jubaw.controller;


import com.jubaw.domain.Customer;
import com.jubaw.domain.OrderItem;
import com.jubaw.domain.Product;

import com.jubaw.service.CustomerService;
import com.jubaw.service.OrderItemService;
import com.jubaw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/orders")
public class OrderItemController {


    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;


    //CREATE ORDER
    //http://localhost:8080/customers + POST
    @PostMapping("/save/filter")
    public ResponseEntity<Map<String, String>> createOrder(@Valid @RequestParam("cid") Long customerId,
                                                           @RequestParam("prod") Long productId,
                                                           @RequestParam("quant") Integer quantity) {


        Customer customer = customerService.getCustomerById(customerId);
        Product product = productService.getProductyById(productId);

        OrderItem orderItem = new OrderItem();
        orderItem.setCustomer(customer);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setTotalPrice(product.getPrice() * quantity);

        orderItemService.createOrder(orderItem);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Order has been created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //UPDATE ORDER BY ORDERID
    @PutMapping("/update/{id}/quantity/{newQuantity}")
    public ResponseEntity<Map<String, String>> updateOrderById(@Valid @RequestParam("{orderId}") Long id,
                                                               @RequestParam("{newQuantity}") Integer newQuantity) {


        orderItemService.updateOrder(id, newQuantity);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Order quantity has been updated successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderItem>> getAll(@RequestParam )


    }


}
