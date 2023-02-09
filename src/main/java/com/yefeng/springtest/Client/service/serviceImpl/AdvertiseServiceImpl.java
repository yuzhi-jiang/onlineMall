package com.yefeng.springtest.Client.service.serviceImpl;



import com.yefeng.springtest.Client.dao.AdvertiseMapper;
import com.yefeng.springtest.Client.entity.Advertise;
import com.yefeng.springtest.Client.entity.AdvertiseType;
import com.yefeng.springtest.Client.service.AdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.yefeng.springtest.util.Constant.*;

@Service(value = "ClientAd")
public class AdvertiseServiceImpl implements AdvertiseService {
    @Resource
    AdvertiseMapper advertiseMapper;

    @Override
    public List<Advertise> getAllAds() {
        return advertiseMapper.findAllAds();
    }

    @Override
    public List<Advertise> getAllAdsByTypeId(Integer tid) {
        return advertiseMapper.findAllAdsByTypeId(tid);
    }

}
