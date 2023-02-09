package com.yefeng.springtest.Client.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private Long itemId;
    private Integer productId;
    private String productName;
    private String picPath;
    private Double price;
    private Integer amount;
    private Integer status;

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", picPath='" + picPath + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
