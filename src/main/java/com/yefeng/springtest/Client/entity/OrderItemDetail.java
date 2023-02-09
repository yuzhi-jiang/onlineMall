package com.yefeng.springtest.Client.entity;

import java.io.Serializable;

public class OrderItemDetail implements Serializable {
    //卖家信息
    private String  sellerName;
    private String  sellerAddress;
    private String  sellerPhoneNum;
    //订单信息
    private String  createTime;
    private String  orderId;
    private String  orderItemId;
    //Item信息
    private String  status;
    private String  price;
    private String  amount;
    private String  brandName;
    private String  shelfLife;
    private String  producer;
    private String  picPath;

    //收件人
    private String buyerName;
    private String buyerAddress;
    private String buyerPhoneNum;

    @Override
    public String toString() {
        return "OrderItemDetail{" +
                "sellerName='" + sellerName + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", sellerPhoneNum='" + sellerPhoneNum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderItemId='" + orderItemId + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                ", brandName='" + brandName + '\'' +
                ", shelfLife='" + shelfLife + '\'' +
                ", producer='" + producer + '\'' +
                ", picPath='" + picPath + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", buyerPhoneNum='" + buyerPhoneNum + '\'' +
                '}';
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerPhoneNum() {
        return sellerPhoneNum;
    }

    public void setSellerPhoneNum(String sellerPhoneNum) {
        this.sellerPhoneNum = sellerPhoneNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerPhoneNum() {
        return buyerPhoneNum;
    }

    public void setBuyerPhoneNum(String buyerPhoneNum) {
        this.buyerPhoneNum = buyerPhoneNum;
    }
}
