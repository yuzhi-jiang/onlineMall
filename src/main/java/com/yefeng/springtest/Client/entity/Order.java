package com.yefeng.springtest.Client.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Order implements Serializable {
    private Long id;
    private Long buyerId;
    private Date createTime;
    private Integer status;
    private OrderItem[] orderItems;

    public OrderItem[] getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItem[] orderItems) {
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", orderItems=" + Arrays.toString(orderItems) +
                '}';
    }
}
