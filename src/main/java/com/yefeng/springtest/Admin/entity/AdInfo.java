package com.yefeng.springtest.Admin.entity;

import java.util.Arrays;

public class AdInfo {
    private Advertise[] advertises;

    @Override
    public String toString() {
        return "AdInfo{" +
                "advertises=" + Arrays.toString(advertises) +
                '}';
    }

    public Advertise[] getAdvertises() {
        return advertises;
    }

    public void setAdvertises(Advertise[] advertises) {
        this.advertises = advertises;
    }
}
