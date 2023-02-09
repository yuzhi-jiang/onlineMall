package com.yefeng.springtest.Client.service;

import com.yefeng.springtest.Client.entity.*;
import com.yefeng.springtest.util.PathUtil;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public ProductType getProductByTypeNameAndLimit(String type,int limit);
//    public ProductType getProductByTypeIdAndLimit(int typeId,int limit);

    /**
     * 得到热卖商品
     * @return
     */
    public List<ProductType>getHotSellProducts();


    public List<ProductBrief> findProductBriefByParams(Map<String,Object> paramMap);


    public Integer findProductBriefCountByParams(Map<String,Object> paramMap);

    public Integer addProduct(ProductBrief productBrief, ProductDetail productDetail);
    public void addProductTX(ProductBrief productBrief, ProductDetail productDetail);

    public Integer removeProduct(Integer[] ids);

    public void updateProductTX(ProductBrief productBrief, ProductDetail productDetail);
    public Integer updateProduct(ProductBrief productBrief, ProductDetail productDetail);
    public ProductDetail findProductDetailByProductId(Long productId);

}
