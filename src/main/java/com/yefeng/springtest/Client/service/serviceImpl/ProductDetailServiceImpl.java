package com.yefeng.springtest.Client.service.serviceImpl;

import com.yefeng.springtest.Client.dao.ProductDetailMapper;
import com.yefeng.springtest.Client.entity.ProductDetail;
import com.yefeng.springtest.Client.service.ProductDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Resource
    private ProductDetailMapper mapper;
    @Override
    public ProductDetail getProductDetailByProductId(Integer id) {
        return mapper.findProductDetailByProductId(id);
    }
}
