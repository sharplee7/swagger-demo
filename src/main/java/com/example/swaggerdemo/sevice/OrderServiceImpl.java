package com.example.swaggerdemo.sevice;

import com.example.swaggerdemo.entity.Order;
import com.example.swaggerdemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public int save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public int delete(String id) {
        return orderRepository.delete(id);
    }

    @Override
    public int modify(Order order) {
        return orderRepository.modify(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
