package com.yefeng.springtest.Client.controller;


import com.yefeng.springtest.Client.entity.ShoppingCarItem;
import com.yefeng.springtest.Client.entity.UserDetail2;
import com.yefeng.springtest.Client.service.ShoppingCarService;
import com.yefeng.springtest.util.UserDetailUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@RestController
@RequestMapping("/shoppingcar")
@Validated
public class ShoppingcarController {


    @Resource
    private ShoppingCarService shoppingCarService;

    /*
    添加购物车
     */
    @RequestMapping("/add")
    public Map<String, Object> add(@NotNull Long productId, @NotNull Integer amount) {
        HashMap<String, Object> map = new HashMap<>();
        UserDetail2 user = UserDetailUtil.getUser();
        if (user != null) {

            Integer Code = shoppingCarService.addToShoppingCar(user.getId(), productId, amount);
            if (Code == CODE_OK) {
                map.put(ERROR_CODE, CODE_OK);
                map.put(ERROR_MSSAGE, "添加购物车成功");
            } else {
                map.put(ERROR_CODE, Code);
                map.put(ERROR_MSSAGE, "添加购物车失败");
            }
        } else {
            map.put(ERROR_CODE, CODE_USER_ERROR);
            map.put(ERROR_MSSAGE, "添加购物车失败，用户未登录");
        }
        System.out.println(map);
        return map;
    }

    @RequestMapping("/remove")
    public Map<String, Object> remove(@NotEmpty @RequestBody Long[] ids) {
        System.out.println("ids:");
        System.out.println(ids);
        HashMap<String, Object> map = new HashMap<>();
        UserDetail2 user = UserDetailUtil.getUser();

        if (user != null) {

            Integer Code = shoppingCarService.removeShoppingCarItems(user.getId(), ids);
            if (Code == CODE_OK) {
                //还要查询还有多少商品在购物车，并返回
                List<ShoppingCarItem> items = shoppingCarService.findShoppingCarItemsByUserId(user.getId());

                map.put(ERROR_CODE, CODE_OK);
                map.put(ERROR_MSSAGE, "删除购物车商品成功");
                if (items != null && items.size() > 0)
                    map.put("shoppingCarItems", items.toArray(new ShoppingCarItem[]{}));
                else
                    map.put("shoppingCarItems", new ShoppingCarItem[]{});
            } else {
                map.put(ERROR_CODE, Code);
                map.put(ERROR_MSSAGE, "删除购物车商品失败");
            }
        } else {
            map.put(ERROR_CODE, CODE_USER_ERROR);
            map.put(ERROR_MSSAGE, "删除购物车商品，用户未登录");
        }
        System.out.println(map);
        return map;
    }


}
