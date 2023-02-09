package com.yefeng.springtest.Client.entity;

import java.io.Serializable;

public class AdvertiseType implements Serializable {
    private int id;
    private String  name;
    private String text;
    private  String description;
    private int status;
    private String time;
    private int number;

    @Override
    public String toString() {
        return "AdvertiseType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", number=" + number +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
