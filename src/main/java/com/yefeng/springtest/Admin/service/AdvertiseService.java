package com.yefeng.springtest.Admin.service;

import com.yefeng.springtest.Admin.entity.Advertise;
import com.yefeng.springtest.Admin.entity.AdvertiseType;

import java.util.List;

public interface AdvertiseService {

    public List<Advertise> getAllAds();

    public Integer addAdvertise(Advertise advertise);

    public Integer addAdvertiseType(AdvertiseType advertiseType);

    public List<AdvertiseType> getAllAdType();
}
