package com.yefeng.springtest.Client.entity;

import java.io.Serializable;

public class OrderInfos implements Serializable {

    private Order[] orders;

    public OrderInfos() {
        this.orders=new Order[]{};
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }


}
