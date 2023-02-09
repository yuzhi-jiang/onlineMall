package com.yefeng.springtest.Client.service;

import com.yefeng.springtest.Client.entity.ShoppingCarItem;

import java.util.List;

public interface ShoppingCarService {
    public Integer addToShoppingCar(Long buyerId, Long productId, Integer amount);

    public List<ShoppingCarItem> findShoppingCarItemsByUserId(Long uid);

    public Integer removeShoppingCarItems(Long buyerId, Long[] ids);
}
