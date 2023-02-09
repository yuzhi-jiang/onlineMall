package com.yefeng.springtest.Client.service;

import com.yefeng.springtest.Client.entity.Advertise;
import com.yefeng.springtest.Client.entity.AdvertiseType;

import java.util.List;

public interface AdvertiseService {

    public List<Advertise> getAllAds();

    public List<Advertise> getAllAdsByTypeId(Integer tid);

}
