package com.yefeng.springtest.Client.dao;

import com.yefeng.springtest.Client.entity.ProductDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface ProductDetailMapper {

    /**
     * @param 商品id
     *获取 商品详情
     * @return ProductDetail
     */

    @Select("select * from product as pr join productdetail as prt on pr.id=#{id} and prt.id=pr.detailId")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "fullname",property = "fullName"),
            @Result(column = "quantity",property = "quantity"),
            @Result(column = "price",property = "price"),
            @Result(column = "picPath",property = "picPath"),
            @Result(column = "brandName",property = "brandName"),
            @Result(column = "shelfLife",property = "shelfLife"),
            @Result(column = "producer",property = "producer"),
            @Result(column = "tel",property = "producerTel"),
            @Result(column = "producerAddress",property = "producerAddress"),
            @Result(column = "description",property = "description"),


            @Result(column = "id",property = "productName" ,one = @One(
                    select = "com.yefeng.springtest.Client.dao.ProductDetailMapper.getProductNameBySellerId",fetchType = FetchType.EAGER)),

            @Result(column = "sellerId",property = "sellerName",one = @One(
                    select = "com.yefeng.springtest.Client.dao.ProductDetailMapper.getSellerNameBySellerId",fetchType = FetchType.EAGER)),

    })
    public ProductDetail findProductDetailByProductId(@Param("id") Integer id);

    /**
     * @param 卖家id
     *获取 卖家姓名
     * @return sellerName
     */
    @Select("select userName from user where id=#{sellerId}")
    public String getSellerNameBySellerId(@Param("sellerId") String sellerId);

    /**
     * @param 商品id
     *获取 商品名称
     * @return ProductDetail
     */
    @Select("select name from product where id=#{id}")
    public String getProductNameBySellerId(@Param("id") String id);
    /**
     * @param 商品类型id
     *获取 商品详情
     * @return ProductDetail
     */
    @Select("select name form producttype where id=#{typeId}")
    public String findTypeByTypeId(@Param("typeId") String typeId);


}
