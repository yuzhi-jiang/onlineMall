package com.yefeng.springtest.Client.service.serviceImpl;

import com.yefeng.springtest.Client.dao.OrderMapper;
import com.yefeng.springtest.Client.dao.ProductMapper;
import com.yefeng.springtest.Client.dao.ShoppingCarMapper;
import com.yefeng.springtest.Client.entity.*;
import com.yefeng.springtest.Client.service.OrderService;
import com.yefeng.springtest.Client.service.ShoppingCarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.yefeng.springtest.util.Constant.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderService orderService;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ShoppingCarMapper shoppingCarMapper;
    @Resource
    private ShoppingCarService shoppingCarService;

    @Override
    @Transactional
    public Integer creat(ShoppingCarItem[] carItems, Long buyerId, Boolean isFromShoppingCar) {
        int len=carItems.length;
        if(carItems==null||len==0){
            return CODE_PRODUCT_BUY_FALSE;
        }
        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setStatus(ORDER_STATUS_PAYED);
        orderMapper.insertMyOrder(order);
        for (ShoppingCarItem shoppingCarItem : carItems) {
            Integer quantity = productMapper.findProductCountByProductId(shoppingCarItem.getProductId());
            if (quantity < shoppingCarItem.getAmount()) {
                throw new RuntimeException();
            }
            quantity = quantity - shoppingCarItem.getAmount();
            productMapper.updataProductByProductId("quantity",quantity,shoppingCarItem.getProductId());
            orderMapper.insertOrderItem(order.getId(), shoppingCarItem.getProductId(),
                    shoppingCarItem.getAmount(), ORDER_STATUS_PAYED);
            if (isFromShoppingCar) {
                shoppingCarMapper.deleteItemsById(shoppingCarItem.getShoppingCarId());
            }
        }
        return CODE_OK;
    }


    @Override
    public void insertOrder(Order order) {
            orderMapper.insertMyOrder(order);
    }

    @Override
    public void insertOrderItem(Long orderId, Long productId, Integer amount, Integer status) {
        orderMapper.insertOrderItem(orderId,productId,amount,status);
    }

    @Override
    public OrderInfos findMyOrdersByUidAndPager(Long uid,Pager pager) {
//        if(role.equals(ROLE_BUYER)){
            OrderInfos orderInfos = new OrderInfos();
            List<Order> orders = orderMapper.findALlOrderByBuyerId(uid,pager);
            if (orders != null) {
                orderInfos.setOrders(orders.toArray(new Order[]{}));
                return orderInfos;
            }
//        }else if(role.equals(ROLE_SELLER)){
//            List<SellerOrderInfo> order = orderMapper.findOrderItemsBySellId(uid);
//            if (order != null) {
//                return order;
//            }
//        }
        return null;
    }

    @Override
    public List<SellerOrderInfo> findMyOrdersBySellerIdAndPager(Long sellId, Pager pager) {
        List<SellerOrderInfo> order = orderMapper.findSellerOrderInfosBySellerIdAndPager(sellId,pager);
        return order;
    }

    @Override
    public Integer findOrderInfosCountByUserId(Long uid) {
        return orderMapper.findOrderInfosCountByUserId(uid);
    }

    @Override
    public Long findOrderInfosCountBySellerId(Long sellerId) {
        return orderMapper.findOrderInfosCountBySellerId(sellerId);
    }

    @Override
    public OrderItemDetail findOrderItemDetailByItemId(Long itemId) {
        try {
            OrderItemDetail itemDetail = orderMapper.findOrderItemDetailByItemId(itemId);
            return itemDetail;
        } catch (Exception e) {
            e.printStackTrace();
            return new OrderItemDetail();
        }

    }
    @Override
    public Integer updateOrderItemStatus(Long itemId, Integer status) {
        try {
            orderMapper.updateOrderItemStatus(itemId, status);
            return CODE_OK;
        } catch (Exception e) {
            return CODE_UPDATE_ORDER_ITEM_FAIL;
        }
    }
}
