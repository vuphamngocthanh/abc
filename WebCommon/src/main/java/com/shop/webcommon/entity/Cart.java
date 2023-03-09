package com.shop.webcommon.entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Order,Integer> orders = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Order,Integer> orders) {
        this.orders = orders;
    }

    public Map<Order,Integer> getOrders() {
        return orders;
    }

    private boolean checkItemInCart(Order order){
        for (Map.Entry<Order, Integer> entry : orders.entrySet()) {
            if(entry.getKey().getId().equals(order.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Order, Integer> selectItemInCart(Order order){
        for (Map.Entry<Order, Integer> entry : orders.entrySet()) {
            if(entry.getKey().getId().equals(order.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Order order){
        if (!checkItemInCart(order)){
            orders.put(order,1);
        } else {
            Map.Entry<Order, Integer> itemEntry = selectItemInCart(order);
            Integer newQuantity = itemEntry.getValue() + 1;
            orders.replace(itemEntry.getKey(),newQuantity);
        }
    }

    public Integer countProductQuantity(){
        Integer productQuantity = 0;
        for (Map.Entry<Order, Integer> entry : orders.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity(){
        return orders.size();
    }

    public long countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Order, Integer> entry : orders.entrySet()) {
            payment += entry.getKey().getProductDetailDto().getPrice() * entry.getKey().getQuantity();
        }
        return (long) payment;
    }
}