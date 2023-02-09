package com.yefeng.springtest.Admin.entity;

import java.io.Serializable;

public class Advertise implements Serializable {
    private int id;
    private int typeId;
    private int sizex;
    private int sizey;
    private String link;
    private String picPath;
    private int status;
    private String time;
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Advertise{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", sizex=" + sizex +
                ", sizey=" + sizey +
                ", link='" + link + '\'' +
                ", picPath='" + picPath + '\'' +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getSizex() {
        return sizex;
    }

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
