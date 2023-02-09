package com.yefeng.springtest.Client.service.serviceImpl;

import com.yefeng.springtest.Client.dao.ProductMapper;
import com.yefeng.springtest.Client.entity.*;
import com.yefeng.springtest.Client.service.ProductService;
import com.yefeng.springtest.util.UploadUtils;
import com.yefeng.springtest.util.UserDetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Lazy
    @Autowired
    private ProductService productService;

    @Override
    public ProductType getProductByTypeNameAndLimit(String type, int limit) {
        return productMapper.findProductsByTypeNameAndLimit(type,limit);
    }


    @Override
    public List<ProductType> getHotSellProducts() {
        Integer[] hots = productMapper.findHotSellTypeIdsByLimit(4);
        ArrayList<ProductType> arrayList = new ArrayList<>();
        for (Integer hot : hots) {
            ProductType productType = productMapper.findProductsByTypeIdAndLimit(hot,4);
//            ProductType productType = productService.getProductByTypeIdAndLimit(hot, 4);
            arrayList.add(productType);
        }
        return arrayList;
    }

    @Override
    public List<ProductBrief> findProductBriefByParams(Map<String,Object> paramMap){

        try {

            List<ProductBrief> productBriefList = productMapper.findProductBriefByParams(paramMap);
            return productBriefList;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Integer findProductBriefCountByParams(Map<String,Object> paramMap){
//        Map<String, Object> params = new HashMap<>();
//        if (typeId != null) {
//            params.put("typeId", typeId);
//        }
//        if (keyWord != null && !keyWord.equals("")) {
//            params.put("keyWord", keyWord);
//        }
//        if (curPage != null && rowsPerPage != null) {
//            Pager pager = new Pager();
//            pager.setCurPage(curPage);
//            pager.setPerPageRows(rowsPerPage);
//            params.put("pager", pager);
//        }
//        if (priceMin != null) {
//            params.put("priceMin", priceMin);
//        }
//        if (priceMax != null) {
//            params.put("priceMax", priceMax);
//        }
        try {

            Integer count = productMapper.findProductBriefCountByParams(paramMap);
            return count;
        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer addProduct(ProductBrief productBrief, ProductDetail productDetail) {
        MultipartFile briefFile = productBrief.getFile();
        //转换文件
        String name = UploadUtils.getUUIDName(briefFile.getOriginalFilename());
        String path = IMAGE_SAVE_PATH + name;
        File file = new File(path);
        //父目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {

            productBrief.getFile().transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            return CODE_STORE_IMG_FILE_FAIL;

        }
        productBrief.setPicPath(name);

        //调用事务插入方法
        try {
            productService.addProductTX(productBrief, productDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return CODE_ADD_PRODUCT_TX_FAIL;
        }
        return CODE_OK;
    }

    @Override
    public Integer updateProduct(ProductBrief productBrief, ProductDetail productDetail) {
        String oldPicPath=productDetail.getPicPath();
        MultipartFile briefFile = productBrief.getFile();
        if(briefFile!=null){
            //转换文件
            String name = UploadUtils.getUUIDName(briefFile.getOriginalFilename());
            String path = IMAGE_SAVE_PATH + name;
            File file = new File(path);
            //父目录
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            productBrief.setPicPath(name);
            try {
                productBrief.getFile().transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("没有收到文件，或文件错误，将使用原来的图片");
                productBrief.setPicPath(oldPicPath);
            }

        }else {

            productBrief.setPicPath(oldPicPath);
        }


        //调用事务更新方法
        try {
            productService.updateProductTX(productBrief, productDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return CODE_UPDATE_PRODUCT_TX_FAIL;
        }
        return CODE_OK;
    }

    @Override
    public ProductDetail findProductDetailByProductId(Long productId) {
        return productMapper.findProductDetailByProductId(productId);
    }

    @Override
    public Integer removeProduct(Integer[] ids) {
        try {
            productMapper.deleteProduct(ids);
            return CODE_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return CODE_PRODUCT_REMOVE_FAIL;
        }
    }

    @Override
    @Transactional
    public void updateProductTX(ProductBrief productBrief, ProductDetail productDetail) {
        productMapper.updateProductDetail(productDetail);

        UserDetail2 user = UserDetailUtil.getUser();

        productBrief.setSellerId(user.getId());
        productBrief.setSellerName(user.getName());

        productMapper.updataProduct(productBrief);
    }
    @Override
    @Transactional
    public void addProductTX(ProductBrief productBrief, ProductDetail productDetail) {
        productMapper.insertProductDetail(productDetail);

        UserDetail2 user = UserDetailUtil.getUser();

        productBrief.setDetailId(productDetail.getId());
        productBrief.setSellerId(user.getId());
        productBrief.setSellerName(user.getName());

        productMapper.insertProduct(productBrief);


    }




}
