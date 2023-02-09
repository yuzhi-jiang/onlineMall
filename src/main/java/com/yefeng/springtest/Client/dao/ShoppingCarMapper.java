package com.yefeng.springtest.Client.dao;

import com.yefeng.springtest.Client.entity.ShoppingCarItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShoppingCarMapper {


//    @Select("select s.id as sid,s.amount,p.id as pid,p.name,p.quantity,p.price,p.picPath,u.username,pr.brandname,pr.shelfLife,pr.tel,pr.producer,pr.producerAddress " +
//            "from product as p " +
//            "LEFT JOIN productdetail as pr on " +
//            "p.id=pr.id " +
//            "join user as u on " +
//            "u.id=p.sellerId" +
//            "join shoppingcar as s on " +
//            "s.productId=p.id " +
//            "where s.id in " +
//            "(select id from shoppingcar where buyerId=#{uid})")
    @Select("select s.id as sid,s.amount,p.id as pid,p.name,p.quantity,p.price,p.picPath,u.username,pr.brandname,pr.shelfLife,pr.tel,pr.producer,pr.producerAddress\n" +
            "from product as p\n" +
            "LEFT JOIN productdetail as pr on\n" +
            "p.id=pr.id\n" +
            " join user as u on\n" +
            "u.id=p.sellerId\n" +
            " join shoppingcar as s on\n" +
            "s.productId=p.id\n" +
            "where s.id in\n" +
            "(select id from shoppingcar where buyerId=#{uid})")
    @Results(id = "shoppingCarItemResult", value = {
            @Result(id = true, column = "sid", property = "shoppingCarId"),
            @Result(column = "pid", property = "productId"),
            @Result(column = "amount", property = "amount"),
            @Result(column = "name", property = "productName"),
            @Result(column = "price", property = "price"),
            @Result(column = "quantity", property = "quantity"),
            @Result(column = "username", property = "sellerName"),
            @Result(column = "picPath", property = "picPath"),
            @Result(column = "brandname", property = "brandName"),
            @Result(column = "shelfLife", property = "shelfLife"),
            @Result(column = "producer", property = "producer"),
            @Result(column = "producerAddress", property = "producerAddress"),
            @Result(column = "tel", property = "producerTel")
    })
    public List<ShoppingCarItem> findShoppingCarItemsByUserId(Long uid);



    @Insert("INSERT INTO shoppingcar VALUES (NULL, #{buyerId}, #{productId}, #{amount})")
    public void insertShoppingCar(@Param("buyerId") Long buyerId,
                                  @Param("productId") Long productId,
                                  @Param("amount") Integer amount);


    @Delete("<script>" +
            "delete from shoppingcar where buyerId=#{buyerId} and id in " +
            "<foreach collection='ids' item='id' index='index' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
   public void removeProductBYids(@Param("buyerId") Long buyerId, @Param("ids") Long[] ids);

//    @Delete("<script>" +
//            "delete from shoppingcar where id in " +
//            "<foreach collection='carId' item='id' index='index' open='(' separator=',' close=')'>" +
//            "#{id}" +
//            "</foreach>" +
//            "</script>")
//    public void deleteItemsById(@Param("carId") Long[] carId);

    @Delete("delete from shoppingcar where id = #{carId} ")
    public void deleteItemsById(@Param("carId") Long carId);
}
