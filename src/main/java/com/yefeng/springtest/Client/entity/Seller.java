package com.yefeng.springtest.Client.entity;

public class Seller {
    private Long id;
    private  String shop_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", shop_name='" + shop_name + '\'' +
                '}';
    }
}
