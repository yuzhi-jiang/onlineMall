package com.yefeng.springtest.Client.entity;

import java.io.Serializable;

public class ShoppingCarItem implements Serializable {
    private Long shoppingCarId;
    private Long productId;
    private String productName;
    private Float price;
    private Integer quantity;
    private String sellerName;
    private String picPath;
    private String brandName;
    private String shelfLife;
    private String producer;
    private String producerAddress;
    private String producerTel;
    private Integer amount;

    @Override
    public String toString() {
        return "ShoppingCarItem{" +
                "shoppingCarId=" + shoppingCarId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sellerName='" + sellerName + '\'' +
                ", picPath='" + picPath + '\'' +
                ", brandName='" + brandName + '\'' +
                ", shelfLife='" + shelfLife + '\'' +
                ", producer='" + producer + '\'' +
                ", producerAddress='" + producerAddress + '\'' +
                ", producerTel='" + producerTel + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Long getShoppingCarId() {
        return shoppingCarId;
    }

    public void setShoppingCarId(Long shoppingCarId) {
        this.shoppingCarId = shoppingCarId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    public String getProducerAddress() {
        return producerAddress;
    }

    public void setProducerAddress(String producerAddress) {
        this.producerAddress = producerAddress;
    }

    public String getProducerTel() {
        return producerTel;
    }

    public void setProducerTel(String producerTel) {
        this.producerTel = producerTel;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
