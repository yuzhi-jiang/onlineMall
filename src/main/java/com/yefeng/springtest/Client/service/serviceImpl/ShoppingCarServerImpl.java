package com.yefeng.springtest.Client.service.serviceImpl;

import com.yefeng.springtest.Client.dao.ShoppingCarMapper;
import com.yefeng.springtest.Client.entity.ShoppingCarItem;
import com.yefeng.springtest.Client.service.ShoppingCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.yefeng.springtest.util.Constant.*;

@Service
public class ShoppingCarServerImpl implements ShoppingCarService {
    @Resource
    ShoppingCarMapper shoppingCarMapper;

    @Override
    public Integer addToShoppingCar(Long buyerId, Long productId, Integer amount) {
        try {
            shoppingCarMapper.insertShoppingCar(buyerId,productId,amount);
            return CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return CODE_PRODUCTSHOPPING_CAR_ADD_FAIL;
        }
    }

    @Override
    public List<ShoppingCarItem> findShoppingCarItemsByUserId(Long uid) {
        List<ShoppingCarItem> items = shoppingCarMapper.findShoppingCarItemsByUserId(uid);
        if(items!=null){
            return items;
        }else {
            return null;
        }
    }

    @Override
    public Integer removeShoppingCarItems(Long buyerId, Long[] ids) {
        try {
            shoppingCarMapper.removeProductBYids(buyerId,ids);
            return CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return CODE_PRODUCT_REMOVE_FAIL;
        }

    }
}
