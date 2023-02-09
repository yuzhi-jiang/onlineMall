package com.yefeng.springtest.Client.entity;

import java.io.Serializable;
import java.util.Date;

public class SellerOrderInfo implements Serializable {
    private Long orderId;
    private Date createTime;
    private Long itemId;
    private Integer amount;
    private Integer status;
    private String picPath;
    private String productName;
    private Long productId;
    private Float price;

    @Override
    public String toString() {
        return "SellerOrderInfo{" +
                "orderId=" + orderId +
                ", createTime=" + createTime +
                ", itemId=" + itemId +
                ", amount=" + amount +
                ", status=" + status +
                ", picPath='" + picPath + '\'' +
                ", productName='" + productName + '\'' +
                ", productId=" + productId +
                ", price=" + price +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
