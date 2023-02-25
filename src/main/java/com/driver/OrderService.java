package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId) {
        orderRepository.addPartner(partnerId);
    }

    public void addPair(String orderId, String partnerId) {
        orderRepository.addPair(orderId, partnerId);
    }

    public Order getOrder(String orderId) {
        return orderRepository.getOrder(orderId);
    }

    public DeliveryPartner getPartner(String partnerId) {
        return orderRepository.getPartner(partnerId);
    }

    public Integer getCount(String partnerId) {
        return orderRepository.getCount(partnerId);
    }

    public List<String> getLists(String partnerId) {
        return orderRepository.getLists(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Integer getCountUnOrders() {
        return orderRepository.getCountUnOrders();
    }

    public Integer ordersLeft(String time, String partnerId) {
        return orderRepository.ordersLeft(time, partnerId);
    }

    public String getLastTime(String partnerId) {
        return orderRepository.lastTime(partnerId);
    }

    public void deletePartner(String partnerId) {
        orderRepository.deletePartner(partnerId);
    }

    public void deleteOrder(String orderId) {
        orderRepository.deleteOrder(orderId);
    }


}
