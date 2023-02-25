package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;
        String hour = deliveryTime.substring(0,2);
        String min = deliveryTime.substring(3);
        int time = Integer.parseInt(hour) * 60 + Integer.parseInt(min);
        this.deliveryTime = time;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
