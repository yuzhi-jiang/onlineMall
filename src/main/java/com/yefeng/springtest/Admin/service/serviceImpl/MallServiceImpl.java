package com.yefeng.springtest.Admin.service.serviceImpl;

import com.yefeng.springtest.Admin.dao.MallMapper;
import com.yefeng.springtest.Admin.service.MallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MallServiceImpl implements MallService {

    @Resource
    private MallMapper mallMapper;
    @Override
    public Long getMallUserCount() {
        return mallMapper.findUserCounts();
    }

    @Override
    public Long getMallOrderCount() {
        return mallMapper.findOrderCounts();
    }

    @Override
    public Double getTotalTransaction() {
        return mallMapper.findTotalAmount();
    }

    @Override
    public Long getProductAmount() {
        return mallMapper.findTotalProductAmount();
    }

    @Override
    public Long getProducAmounttByStatus(Integer stauts) {
        return mallMapper.findProdcutAmountByStatus(stauts);
    }

    @Override
    public Long getOrderAmountByStatus(Integer status){

        return mallMapper.findOrderAmountByStatus(status);

    }



}
