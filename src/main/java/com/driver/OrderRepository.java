package com.driver;

import org.springframework.stereotype.Repository;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Repository
public class OrderRepository {
    HashMap<String, Order> orderMap = new HashMap<>();
    HashMap<String, Order> orderMapUn = new HashMap<>();
    HashMap<String, DeliveryPartner> partnerMap = new HashMap<>();
    HashMap<String, List<Order>> pairMap = new HashMap<>();


    public void addOrder(Order order) {
        orderMap.put(order.getId(), order);
        orderMapUn.put(order.getId(), order);
    }

    public void addPartner(String partnerId) {
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        partnerMap.put(partnerId, partner);
    }

    public void addPair(String orderId, String partnerId) {
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            Order order = orderMap.get(orderId);
            if(pairMap.containsKey(partnerId)){
                pairMap.get(partnerId).add(order);
                orderMapUn.remove(orderId);
                DeliveryPartner partner = partnerMap.get(partnerId);
                partner.setNumberOfOrders(pairMap.get(partnerId).size());
            }
            else{
                List<Order> orders = new ArrayList<>();
                orders.add(order);
                pairMap.put(partnerId, orders);
                orderMapUn.remove(orderId);
                DeliveryPartner partner = partnerMap.get(partnerId);
                partner.setNumberOfOrders(1);
            }
        }
    }

    public Order getOrder(String orderId) {
        return orderMap.getOrDefault(orderId, null);

    }

    public DeliveryPartner getPartner(String partnerId) {
        return partnerMap.getOrDefault(partnerId,null);
    }

    public Integer getCount(String partnerId) {
        int count = 0;
        if(partnerMap.containsKey(partnerId)){
            count = partnerMap.get(partnerId).getNumberOfOrders();
        }
        return count;
    }

    public List<String> getLists(String partnerId) {
        List<String> count = new ArrayList<>();
        if(pairMap.containsKey(partnerId)){
            //List<Order> orders = pairMap.get(partnerId);
            //count = pairMap.get(partnerId);
            for(Order order : pairMap.get(partnerId)){
                count.add(order.getId());
            }
        }
        return count;
    }

    public List<String> getAllOrders() {
        List<String> allOrders = new ArrayList<>();
        for(String orderName : orderMap.keySet()){
            allOrders.add(orderName);
        }
        return allOrders;
    }

    public Integer getCountUnOrders() {
        //int count = 0;
        //count = orderMapUn.size();
        return orderMapUn.size();
//        for(String s : orderMap.keySet()){
//            for(List<Order> orders : pairMap.values()){
//                for(Order order : orders){
//                    if(order.getId().equals(s)) count++;
//                }
//            }
//        }
//        return orderMapUn.size();
    }

    public Integer ordersLeft(String time, String partnerId) {
        int count = 0;
        String hour = time.substring(0,2);
        String min = time.substring(3,5);
        int givenTime = Integer.parseInt(hour) * 60 + Integer.parseInt(min);
        if(pairMap.containsKey(partnerId)){
            for(Order order : pairMap.get(partnerId)){
                if(order.getDeliveryTime() > givenTime) count++;
            }
        }
        return count;
    }

    public String lastTime(String partnerId) {
        String time = "";
        int max = -1;
        if(pairMap.containsKey(partnerId)){
            for(Order order : pairMap.get(partnerId)){
                max = Math.max(max, order.getDeliveryTime());
            }
        }
        int mins = max % 60;
        int hours = max / 60;
        String min = Integer.toString(mins);
        if(mins < 10){
            min = "0"+min;
        }
        String hr = Integer.toString(hours);
        if(hours < 10){
            hr = "0"+hr;
        }
        time = hr+":"+min;
        return time;
    }

    public void deletePartner(String partnerId) {
        if(pairMap.containsKey(partnerId)){
            for(Order order : pairMap.get(partnerId)){
                orderMapUn.put(order.getId(), order);
            }
            pairMap.remove(partnerId);
        }
        partnerMap.remove(partnerId);
    }

    public void deleteOrder(String orderId) {
        for(String s : pairMap.keySet()){
            List<Order> list = pairMap.get(s);
            Iterator<Order> iterator = list.iterator();
            while(iterator.hasNext()){
                Order order = iterator.next();
                if(order.getId().equals(orderId)){
                    iterator.remove();
                    partnerMap.get(s).setNumberOfOrders(pairMap.get(s).size());
                }
            }
        }
        orderMap.remove(orderId);
        orderMapUn.remove(orderId);
    }
}
