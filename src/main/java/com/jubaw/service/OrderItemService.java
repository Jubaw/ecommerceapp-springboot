package com.jubaw.service;

import com.jubaw.domain.OrderItem;
import com.jubaw.exceptions.ResourceNotFound;
import com.jubaw.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;


    //CREATE ORDER
    public void createOrder(OrderItem orderItem) {
        boolean sameOrderExists = orderItemRepository.existsByProductName(orderItem.getProduct().getName());
        if (sameOrderExists) {
            orderItem.setQuantity(orderItem.getQuantity() + 1);
        } else {
            orderItemRepository.save(orderItem);
        }


    }



    public void updateOrder(Long id, Integer newQuantity) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(()->new ResourceNotFound("No order found with given ID " + id));

        orderItem.setQuantity(newQuantity);
        orderItem.setTotalPrice(orderItem.getProduct().getPrice() * newQuantity);
        orderItemRepository.save(orderItem);
    }
}
