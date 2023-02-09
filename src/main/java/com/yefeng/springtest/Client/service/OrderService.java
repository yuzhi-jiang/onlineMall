package com.yefeng.springtest.Client.service;

import com.yefeng.springtest.Client.entity.*;

import java.util.List;

public interface OrderService {


        public Integer creat(ShoppingCarItem[] carItems,Long uid,Boolean isFromShoppingCar);
        public void insertOrder(Order order);
        public void insertOrderItem( Long orderId,
                                     Long productId,
                                   Integer amount,
                                   Integer status);

        public OrderInfos findMyOrdersByUidAndPager(Long uid, Pager pager);
        public List<SellerOrderInfo> findMyOrdersBySellerIdAndPager(Long sellId, Pager pager);
        public Integer findOrderInfosCountByUserId(Long uid);
        public Long findOrderInfosCountBySellerId(Long sellerId);
        public OrderItemDetail findOrderItemDetailByItemId(Long itemId);
        public Integer updateOrderItemStatus(Long itemId, Integer status);
}
