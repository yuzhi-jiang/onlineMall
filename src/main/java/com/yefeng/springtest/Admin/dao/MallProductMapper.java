package com.yefeng.springtest.Admin.dao;

import com.yefeng.springtest.Admin.entity.MallProduct;
import com.yefeng.springtest.Client.entity.ProductBrief;
import com.yefeng.springtest.Client.entity.ProductDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MallProductMapper {

    @Select("select p.id,name,price,pr.producerAddress as address,createTime as time,`status`,checkstatus from product as p\n" +
            "left join productdetail as pr on p.detailId=pr.id")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "price",property = "originalPrice"),
            @Result(column = "price",property = "currentPrice"),
            @Result(column = "address",property = "address"),
            @Result(column = "time",property = "time"),
            @Result(column = "status",property = "status"),
            @Result(column = "checkstatus",property = "checkStatus"),
    })
    public List<MallProduct> findAllMallProduct();


    @Select("select p.id,name,price,pr.producerAddress as address,createTime as time,`status`,checkstatus from product as p\n" +
            "left join productdetail as pr on p.detailId=pr.id\n" +
            "where typeId=#{tid}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "price",property = "originalPrice"),
            @Result(column = "price",property = "currentPrice"),
            @Result(column = "address",property = "address"),
            @Result(column = "time",property = "time"),
            @Result(column = "status",property = "status"),
            @Result(column = "checkstatus",property = "checkStatus"),
    })
    public List<MallProduct> findAllMallProdcutByTypeId(@Param("tid")Integer tid);



    @Insert("INSERT INTO product VALUES (NULL, #{productName}, #{price}, #{quantity}, #{detailId}, " +
            "#{typeId}, #{sellerId}, #{picPath})")
    public void insertProduct(ProductBrief product);

    @Insert("INSERT INTO productdetail VALUES (NULL, #{fullName}, #{brandName}, #{shelfLife}, " +
            "#{producer}, #{producerAddress}, #{producerTel}, #{description})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    public void insertProductDetail(ProductDetail productDetail);
}
