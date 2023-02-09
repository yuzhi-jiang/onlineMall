package com.yefeng.springtest.Admin.service;

public interface MallService {


    public Long getMallUserCount();

    public Long getMallOrderCount();

    //总交易金额
    public Double getTotalTransaction();


    ///////商品信息模块

    public Long getProductAmount();

    //根据状态获取商品数量
    public Long getProducAmounttByStatus(Integer stauts);

    /////订单模块
    public Long getOrderAmountByStatus(Integer status);


}
