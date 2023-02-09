package com.yefeng.springtest.Client.entity;

public class ProductDetail {
    private   Long id;
    private   String productName;
    private   double price;
    private   Integer quantity;
    private   String sellerName;
    private   String picPath;
    private   String brandName;
    private   String shelfLife;
    private   String producer;
    private   String producerAddress;
    private   String producerTel;
    private   String fullName;
    private   String description;

    private Long productId;
    private Integer typeId;
    private Integer amount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
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
                ", fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
