package com.yefeng.springtest.Admin.service.serviceImpl;

import com.yefeng.springtest.Admin.dao.MallProductMapper;
import com.yefeng.springtest.Admin.entity.MallProduct;
import com.yefeng.springtest.Admin.service.MallProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MallProdcutServiceImpl implements MallProductService {


    @Resource
    private MallProductMapper mallProductMapper;

    @Override
    public List<MallProduct> getAllProduct() {
        return mallProductMapper.findAllMallProduct();
    }

    @Override
    public List<MallProduct> getAllProductByTypeId(Integer typeId) {
        return mallProductMapper.findAllMallProdcutByTypeId(typeId);
    }
}
