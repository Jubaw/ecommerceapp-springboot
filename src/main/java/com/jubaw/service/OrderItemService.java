package com.jubaw.service;

import com.jubaw.domain.OrderItem;
import com.jubaw.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void createOrder(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }



}
