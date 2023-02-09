package com.yefeng.springtest.Admin.service.serviceImpl;

import com.yefeng.springtest.Admin.dao.AdvertiseMapper;
import com.yefeng.springtest.Admin.entity.Advertise;
import com.yefeng.springtest.Admin.entity.AdvertiseType;
import com.yefeng.springtest.Admin.service.AdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.yefeng.springtest.util.Constant.*;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {
    @Resource
    AdvertiseMapper advertiseMapper;

    @Override
    public List<Advertise> getAllAds() {
        return advertiseMapper.findAllAds();
    }

    @Override
    public Integer addAdvertise(Advertise advertise) {
        try {
            Integer typeid = advertiseMapper.findAdIdByName(advertise.getType());
            advertise.setTypeId(typeid);
            advertiseMapper.insertAdvertise(advertise);
            return CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return AD_ADD_FAIL;
        }
    }

    @Override
    public Integer addAdvertiseType(AdvertiseType advertiseType) {
        try {
            advertiseMapper.insertAdType(advertiseType);
            return CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return AD_ADD_TYPE_FAIL;
        }
    }

    @Override
    public List<AdvertiseType> getAllAdType() {
        return advertiseMapper.findAllAdType();
    }
}
