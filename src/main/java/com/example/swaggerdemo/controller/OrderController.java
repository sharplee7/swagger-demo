package com.example.swaggerdemo.controller;

import com.example.swaggerdemo.dto.OrderRequest;
import com.example.swaggerdemo.dto.OrderResponse;
import com.example.swaggerdemo.entity.Order;
import com.example.swaggerdemo.sevice.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public int saveOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setId(orderRequest.getId());
        order.setQuantity(orderRequest.getQuantity());
        order.setStatus("order");
        order.setComplete(false);

        return orderService.save(order);
    }

    @DeleteMapping("/order/{id}")
    public int deleteOrder(String id) {
        return orderService.delete(id);
    }

    @GetMapping("/order/{id}")
    public OrderResponse getOrder(@PathVariable String id) {
        OrderResponse orderResponse = new OrderResponse();
        Order order = orderService.findById(id);

        orderResponse.setId(order.getId());
        orderResponse.setQuantity(order.getQuantity());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setDate(order.getDate());
        orderResponse.setComplete(order.isComplete());

        return orderResponse;
    }

    @GetMapping("/order")
    public List<OrderResponse> getOrders() {
        ModelMapper modelMapper = new ModelMapper();
        List<OrderResponse> postDtoList = Arrays.asList(modelMapper.map(orderService.findAll(), OrderResponse[].class));
        return postDtoList;
    }
}
