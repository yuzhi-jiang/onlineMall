package com.yefeng.springtest.Admin.entity;

import java.io.Serializable;


public class MallProduct implements Serializable {
    private Long id;
    private String name;
    private Double originalPrice;
    private Double currentPrice;
    private String address;
    private String time;
    private Integer status;
    private Integer checkStatus;//管理员审核状态


    @Override
    public String toString() {
        return "MallProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalPrice=" + originalPrice +
                ", currentPrice=" + currentPrice +
                ", address='" + address + '\'' +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", checkStatus=" + checkStatus +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }
}
