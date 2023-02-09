package com.yefeng.springtest.Client.dao;


import com.yefeng.springtest.Client.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderMapper {


    //添加订单
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("INSERT INTO myorder VALUES (NULL, #{buyerId}, NOW(), #{status})")
    public void insertMyOrder(Order order);

    @Insert("INSERT INTO orderitem VALUES (NULL, #{orderId}, #{productId}, #{amount}, #{status})")
    public void insertOrderItem(@Param("orderId") Long orderId,
                                @Param("productId") Long productId,
                                @Param("amount") Integer amount,
                                @Param("status") Integer status);


    //根据用户id查询用户所有订单id
    @Select("select id,createTime,`status` from myorder where buyerId=#{uid} LIMIT #{pager.firstLimitParam}, #{pager.perPageRows}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "orderItems",
                    many = @Many(
                            select = "com.yefeng.springtest.Client.dao.OrderMapper.findOrderItemsByOrderId",
                            fetchType = FetchType.EAGER))

    })
    public List<Order> findALlOrderByBuyerId(@Param("uid") Long uid,@Param("pager") Pager pager) ;

    @Select("select ord.id as id,ord.productId as productId,amount,ord.`status` as `status`,price,picPath,`name` as productName\n" +
            "from orderitem as ord\n" +
            "left join product as p on\n" +
            "ord.productId=p.id\n" +
            "left join myorder as my on\n" +
            "my.id=ord.orderId\n" +
            "where orderId =#{oid}")
    @Results({
            @Result(id = true, column = "id", property = "itemId"),
            @Result(column = "productId", property = "productId"),
            @Result(column = "productName", property = "productName"),
            @Result(column = "price", property = "price"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "status", property = "status"),
            @Result(column = "picPath", property = "picPath")
    })
    public List<OrderItem> findOrderItemsByOrderId(@Param("oid") Long oid);


    @Select("SELECT i.id AS orderItemId, i.amount, i.status, o.id AS orderId, o.createTime, " +
            "s.username AS sellerName, s.phoneNum AS sellerPhoneNum, " +
            "s.address AS sellerAddress, b.username AS buyerName, b.phoneNum AS buyerPhoneNum, " +
            "b.address AS buyerAddress, p.name AS productName, p.price, p.picPath, " +
            "d.brandname AS brandName, d.shelfLife, d.producer, d.producerAddress, d.tel AS producerTel " +
            "FROM orderitem AS i LEFT JOIN myorder AS o ON i.orderId = o.id " +
            "LEFT JOIN user AS b ON o.buyerId = b.id " +
            "LEFT JOIN product AS p ON i.productId = p.id " +
            "LEFT JOIN user AS s ON p.sellerId = s.id " +
            "LEFT JOIN productdetail AS d ON p.detailId = d.id " +
            "WHERE i.id = #{itemId}")
    public OrderItemDetail findOrderItemDetailByItemId(@Param("itemId") Long itemId);

    @Update("UPDATE orderitem SET status = #{status} WHERE id = #{itemId}")
    public void updateOrderItemStatus(@Param("itemId") Long itemId, @Param("status") Integer status);

    @Select("SELECT COUNT(*) FROM myorder WHERE buyerId = #{uid}")
    public Integer findOrderInfosCountByUserId(Long uid);


    //查找卖家订单
    @Select("SELECT i.id AS iid, i.amount, i.status, o.id AS oid, o.createTime, p.name, p.price, p.picPath " +
            "FROM orderitem AS i LEFT JOIN myorder AS o ON i.orderId = o.id " +
            "LEFT JOIN product AS p ON i.productId = p.id " +
            "WHERE p.sellerId = #{sellerId} " +
            "LIMIT #{pager.firstLimitParam}, #{pager.perPageRows}")
    @Results(id = "sellerOrderInfoResults", value = {
            @Result(id = true, column = "iid", property = "itemId"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "status", property = "status"),
            @Result(column = "oid", property = "orderId"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "name", property = "productName"),
            @Result(column = "price", property = "price"),
            @Result(column = "picPath", property = "picPath")
    })
    public List<SellerOrderInfo> findSellerOrderInfosBySellerIdAndPager(@Param("sellerId") Long sellerId,
                                                                        @Param("pager") Pager pager);

    @Select("select count(*)\n" +
            "from myorder as m\n" +
            "left join orderitem as o on o.orderId=m.id\n" +
            "left join product as p on p.id= o.productId\n" +
            "left join user as u on u.id=p.sellerId\n" +
            "where u.id=#{sellerId}")
    public Long findOrderInfosCountBySellerId(@Param("sellerId") Long sellerId);
}
