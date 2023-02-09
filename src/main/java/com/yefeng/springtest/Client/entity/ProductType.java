package com.yefeng.springtest.Client.entity;

import java.util.List;

public class ProductType {
    int id;
    String type;
    List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductType:{" +
                "type='" + type + '\'' +
                ", products=" + products.toString() +
                '}';
    }
}
