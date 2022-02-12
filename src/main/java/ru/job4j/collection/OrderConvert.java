package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;

public class OrderConvert {
    public static HashMap<String, Order> process(List<Order> orders) {
        HashMap<String, Order> map = new HashMap<>();
        int index = 0;
        for (Order order : orders) {
            map.put(order.getNumber(), orders.get(index));
            index++;
        }
        return map;
    }
}