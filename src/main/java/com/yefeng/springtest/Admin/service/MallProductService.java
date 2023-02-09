package com.yefeng.springtest.Admin.service;

import com.yefeng.springtest.Admin.entity.MallProduct;

import java.util.List;

public interface MallProductService {


    public List<MallProduct> getAllProduct();
    public List<MallProduct> getAllProductByTypeId(Integer typeId);



}
