package com.yefeng.springtest.Admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MallMapper {
//    1.获取商城用户
//    select count(*) from user_role where roleId!=3

    @Select("select count(*) from user_role where roleId!=3")
    public Long findUserCounts();
//            2.商城订单数
//    select count(*) from myorder

    @Select("select count(*) from myorder")
    public Long findOrderCounts();
//3 交易记录（总金额）
//    select sum(amount*price) from orderitem as o
//    left join product as p on p.id=o.productId

    @Select("select sum(amount*price) from orderitem as o\n" +
            "    left join product as p on p.id=o.productId")
    public Double findTotalAmount();




    ///////////////////////////商品统计信息
    //1.商品总数
    //select count(*) from product;
    //2.下架商品数
    //select count(*) from product where status=
    //3.上架商品数
    //select count(*) from product where status=


    @Select("select count(*) from product")
    public Long findTotalProductAmount();

    @Select("select count(*) from product where status=#{status}")
    public Long findProdcutAmountByStatus(@Param("status")Integer status);





    ///////////////////////////////////订单统计信息
    //1.未处理订单
    //2.待发货订单
    //select count(*) from orderItem where status=
    //3.待结算订单
    //select count(*) from orderItem where status=
    //4.已成交订单
    //select count(*) from orderItem where status=
    //5.交易失败订单数
    //select count(*) from orderItem where status=




    @Select("select count(*) from orderItem where status=#{status}")
    public Long findOrderAmountByStatus(@Param("status")Integer status);
}
