package com.yefeng.springtest.Client.dao;

import com.yefeng.springtest.Client.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    @Select("select * from product where #{param}=#{val}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "price", property = "price"),
            @Result(column = "quantity", property = "quantity"),
            @Result(column = "detailId", property = "detailid"),
            @Result(column = "picPath", property = "picPath"),
            @Result(column = "typeId", property = "typeId")
    })
    public List<Product> findProductsByParam(Map<String, Object> map);

    @Select("select * from product where typeId=#{typeId} limit 4")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "price", property = "price"),
            @Result(column = "quantity", property = "quantity"),
            @Result(column = "detailId", property = "detailId"),
            @Result(column = "picPath", property = "picPath"),
            @Result(column = "typeId", property = "typeId")
    })
    public List<Product> findProductsByTypeId(@Param("typeId") int typeId);


    @Select("select name form producttype where id=#{typeId}")
    public String findTypeByTypeId(@Param("typeId") String typeId);


    @Select("select * from producttype where name=#{typeName}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "type"),
            @Result(column = "{param=typeId,val=id}", property = "products",
                    many = @Many(
                            select = "com.yefeng.springtest.Client.dao.ProductMapper.findProductsByParam", fetchType = FetchType.EAGER)
            )
    })
    public ProductType findProductsByTypeNameAndLimit(@Param("typeName") String typeName, @Param("Limit") int Limit);


    @Select("select * from producttype where id=#{typeId} limit #{limit}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "type"),
            @Result(column = "id", property = "products", javaType = ArrayList.class,
                    many = @Many(
                            select = "com.yefeng.springtest.Client.dao.ProductMapper.findProductsByTypeId", fetchType = FetchType.EAGER)
            )
    })
    public ProductType findProductsByTypeIdAndLimit(@Param("typeId") int typeId, @Param("limit") int limit);


    @Select("select typeid from hot  order by typeid desc limit #{limit}")
    public Integer[] findHotSellTypeIdsByLimit(@Param("limit") int limit);

    @Select("select name from producttype join hot on producttype.id=hot.typeId limit #{limit}")
    public String[] findHotSellTypeNamesByLimit(@Param("limit") int limit);

    @Select("select * from hot  order by typeid desc limit #{limit}")
    public List<Hot> findHotsByLimit(@Param("limit") int limit);


    //查询购物商品根据ids
    @Select({"<script>",
            "SELECT p.id, p.name, p.price, p.quantity, u.username, p.picPath, d.brandname, " +
                    "d.shelfLife, d.producer, d.producerAddress, d.tel " +
                    "FROM product AS p LEFT JOIN user AS u ON p.sellerId = u.id LEFT JOIN productdetail AS d ON " +
                    "p.detailId = d.id WHERE p.id IN ",
            "<foreach collection='list' item='id' index='index' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    @Results(id = "productItemResult", value = {
            @Result(id = true, column = "id", property = "productId"),
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
    public List<ShoppingCarItem> findProductItemsByIds(List<Long> ids);



    @SelectProvider(type = ProductDynaSqlProvider.class, method = "findProductBriefByParams")
    @Results(id = "productBriefResult", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "productName"),
            @Result(column = "price", property = "price"),
            @Result(column = "username", property = "sellerName"),
            @Result(column = "picPath", property = "picPath"),
            @Result(column = "quantity", property = "quantity")
    })
    public List<ProductBrief> findProductBriefByParams(Map<String, Object> params);

    @SelectProvider(type = ProductDynaSqlProvider.class, method = "findProductBriefCountByParams")
    public Integer findProductBriefCountByParams(Map<String ,Object>params);

    @Select("select quantity from product where id=#{pid}")
    public Integer findProductCountByProductId(@Param("pid") Long pid);

    @Update("update product set ${key}=#{val} where id=#{pid}")
    public void updataProductByProductId(@Param("key") String key,@Param("val") Object val,@Param("pid") Long pid);



    @Insert("INSERT INTO product(name,price,quantity,detailId,typeId,sellerId,picPath) VALUES( #{productName}, #{price}, #{quantity}, #{detailId}, " +
            "#{typeId}, #{sellerId}, #{picPath})")
    public void insertProduct(ProductBrief product);

    @Insert("INSERT INTO productdetail VALUES (NULL, #{fullName}, #{brandName}, #{shelfLife}, " +
            "#{producer}, #{producerAddress}, #{producerTel}, #{description})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    public void insertProductDetail(ProductDetail productDetail);


    ///商品更新
    @Select("select p.id as productId,detailId as id,p.`name` as productName,price,quantity,typeid\n" +
            ",fullname,brandname,shelfLife,producer,producerAddress,\n" +
            "d.tel as producerTel,description,picPath\n" +

            "from product as p\n" +
            "left join productdetail as d on d.id=p.detailId\n" +
            "where p.id=#{productId}")
    public ProductDetail findProductDetailByProductId(@Param("productId")Long productId);

    @Update("update product set \n" +
            "`name`=#{productName}, \n" +
            "price=#{price},\n" +
            "quantity=#{quantity},\n" +
            "detailId=#{detailId}, \n" +
            "typeId=#{typeId},\n" +
            "sellerId=#{sellerId},\n" +
            "picPath=#{picPath}\n" +
            "where id=#{id}")
    public void updataProduct(ProductBrief product);

    @Update("update productdetail set\n" +
            "fullName=#{fullName}, brandName=#{brandName}, shelfLife=#{shelfLife},\n" +
            "producer=#{producer}, producerAddress=#{producerAddress}, tel=#{producerTel}, description=#{description}\n" +
            " where id=#{id}")
    public void updateProductDetail(ProductDetail productDetail);

    @Delete("<script>" +
            "delete from product where id in" +
            "<foreach collection='ids' item='id' index='index' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    public void deleteProduct(@Param("ids") Integer[] ids);
}
