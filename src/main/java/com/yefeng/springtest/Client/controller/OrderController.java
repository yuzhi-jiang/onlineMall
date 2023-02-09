package com.yefeng.springtest.Client.controller;


import com.yefeng.springtest.Client.entity.*;
import com.yefeng.springtest.Client.service.OrderService;
import com.yefeng.springtest.Client.service.ProductDetailService;
import com.yefeng.springtest.util.UserDetailUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public Map<String, Object> creat(@RequestBody ShoppingCarItem[] shoppingCarItems, @RequestParam Boolean isFromShoppingCar) {
        Map<String, Object> map = new HashMap<>();

//        ShoppingCarItem[] shoppingCarItems = infos.getShoppingCarItems();
        for (ShoppingCarItem shoppingCarItem : shoppingCarItems) {
            System.out.println(shoppingCarItem.toString());
        }

        try {
            UserDetail2 user = UserDetailUtil.getUser();
            Integer status = orderService.creat(shoppingCarItems, user.getId(), isFromShoppingCar);
            map.put(ERROR_CODE, status);
            map.put(ERROR_MSSAGE, "购买成功");
        } catch (Exception e) {

            map.put(ERROR_CODE, CODE_PRODUCT_BUY_FALSE);
            map.put(ERROR_MSSAGE, "购买失败");

            e.printStackTrace();
        }
        System.out.println(map);
        return map;
    }

    @RequestMapping("/itemDetail")
    public ModelAndView itemDetail(Long itemId, String role) {
        System.out.println("itemId=" + itemId);
        System.out.println("role=" + role);
        ModelAndView modelAndView = new ModelAndView();
        OrderItemDetail detail = orderService.findOrderItemDetailByItemId(itemId);
        System.out.println("detail:" + detail);
        modelAndView.addObject("orderItemDetail", detail);
        modelAndView.addObject("role", role);

        modelAndView.setViewName("orderItemDetail");
        return modelAndView;
    }

    @Resource
    private ProductDetailService productDetailService;

    @RequestMapping("product/detail")
    public Map<String, Object> productDetail(Integer id) {
        ProductDetail productDetail = productDetailService.getProductDetailByProductId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put(ERROR_CODE, CODE_OK);
        map.put(ERROR_MSSAGE, "查询成功");
        map.put("productDetail", productDetail);
        return map;
    }

    //    updateStatus
    @RequestMapping("/updateStatus")
    public Map<String, Object> productDetail(Long itemId, Integer status) {
        HashMap<String, Object> map = new HashMap<>();
        Integer Code = orderService.updateOrderItemStatus(itemId, status);
        if (Code == CODE_OK) {
            map.put(ERROR_CODE, CODE_OK);
            map.put(ERROR_MSSAGE, "状态修改成功");
        } else {
            map.put(ERROR_CODE, Code);
            map.put(ERROR_MSSAGE, "状态修改失败");
        }

        return map;
    }
}
