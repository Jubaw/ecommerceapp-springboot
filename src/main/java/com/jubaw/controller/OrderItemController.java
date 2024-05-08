package com.jubaw.controller;


import com.jubaw.domain.Customer;
import com.jubaw.domain.OrderItem;
import com.jubaw.domain.Product;

import com.jubaw.service.CustomerService;
import com.jubaw.service.OrderItemService;
import com.jubaw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    //http://localhost:8080/orders/save/filter + POST
    @PostMapping("/save")
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

    //GET ORDER
   // http://localhost:8080/orders + GET
    @GetMapping()
    public ResponseEntity<List<OrderItem>> getAll(){
        List<OrderItem> orderItems = orderItemService.getAllOrders();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/page") // http://localhost:8080/students/page?page=0&size=2&sort=name&direction=ASC
    public ResponseEntity<Page<OrderItem>> getAllWithPage(
            @RequestParam("page") int page, // kacinci sayfa gelecek
            @RequestParam("size") int size, // page basi kac nesne
            @RequestParam(value = "sort",defaultValue = "id") String  prop, // siralamada kullanilacak degisken
            @RequestParam("direction") Sort.Direction direction // siralama yonu
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<OrderItem> studentPage = orderItemService.getAllWithPage(pageable);
        return ResponseEntity.ok(studentPage);
    }
    //DELETE ORDER BY ID
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrderById(@RequestParam("id") Long id){
       orderItemService.deleteOrderById(id);
       return ResponseEntity.ok("Order has been deleted");

    }




}
