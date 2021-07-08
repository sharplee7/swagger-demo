package com.example.swaggerdemo.mock;

import com.example.swaggerdemo.entity.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderDB {
    private static OrderDB orderDB = null;
    private Map<String, Order> persistence = new ConcurrentHashMap<String, Order>();

    static public OrderDB getInstance() {
        if (orderDB == null) {
            orderDB = new OrderDB();
        }
        return orderDB;
    }

    public Order findById(String id) {
        return persistence.get(id);
    }

    public int save(Order order) {
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        order.setDate(format.format(time));

        persistence.put(order.getId(), order);

        return 1;
    }

    public int delete(String id) {
        persistence.remove(id);

        return 1;
    }

    public List<Order> findAll(){
        List<Order> list = new ArrayList(persistence.values());
        return list;
    }

}
