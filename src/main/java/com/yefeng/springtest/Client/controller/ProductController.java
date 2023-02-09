package com.yefeng.springtest.Client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yefeng.springtest.Client.entity.Pager;
import com.yefeng.springtest.Client.entity.ProductBrief;
import com.yefeng.springtest.Client.entity.ProductDetail;
import com.yefeng.springtest.Client.service.ProductDetailService;
import com.yefeng.springtest.Client.service.ProductService;
import com.yefeng.springtest.util.UserDetailUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductDetailService service;
    ObjectMapper mapper = new ObjectMapper();
    @RequestMapping("/detail")
    public Map<String,Object> getDetailById( Integer id){
        System.out.println("id="+id);
        HashMap<String, Object> map = new HashMap<>();
        if(id!=null) {

            try {
                ProductDetail detail = service.getProductDetailByProductId(id);

                map.put("productDetail",detail);
                map.put(ERROR_CODE,CODE_OK);

            } catch (Exception e) {
                e.printStackTrace();
                map.put(ERROR_MSSAGE,"获取上商品详细信息失败");
                map.put(ERROR_CODE,Code_PorductDetail_FAIL);
            }

        }
        System.out.println(map);
        return map;

    }

    @Resource
    ProductService productService;

    @RequestMapping("/list")
    public Map<String, Object> listProduct(Long typeId, String keyWord,
                                           @Min(1) Integer curPage,
                                           @Min(1) Integer rowsPerPage,
                                           Integer priceMin,
                                           Integer priceMax) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> params = new HashMap<>();
            if (typeId != null) {
                params.put("typeId", typeId);
            }
            if (keyWord != null && !keyWord.equals("")) {
                params.put("keyWord", keyWord);
            }
            if (curPage != null && rowsPerPage != null) {
                Pager pager = new Pager();
                pager.setCurPage(curPage);
                pager.setPerPageRows(rowsPerPage);
                params.put("pager", pager);
            }
            if (priceMin != null) {
                params.put("priceMin", priceMin);
            }
            if (priceMax != null) {
                params.put("priceMax", priceMax);
            }

            List<ProductBrief> productList = productService.findProductBriefByParams(params);
            System.out.println(productList);

            //符合类型的商品有多少个，方便分页
            Integer count = productService.findProductBriefCountByParams(params);

            System.out.println("有"+count+"个");
            if (productList != null && count >= 0) {
                Integer totalPage = (count + rowsPerPage - 1) / rowsPerPage;
                System.out.println("有"+totalPage+"页");
                map.put(ERROR_CODE, CODE_OK);
                map.put("productList", productList);
                map.put("totalPage", totalPage);
            }else {
                map.put(ERROR_CODE, CODE_PRODUCT_QUERY_FAIL);
            }
        }catch (Exception e){
            map.put(ERROR_CODE, CODE_PRODUCT_QUERY_FAIL);
        }

        return map;
    }



    @RequestMapping("/add")
    public Map<String ,Object> add(ProductDetail productDetail,ProductBrief brief){
        HashMap<String, Object> map = new HashMap<>();

        Integer code = productService.addProduct(brief, productDetail);
        if (code == CODE_OK) {
            map.put(ERROR_CODE, CODE_OK);
            map.put(ERROR_MSSAGE, "添加商品成功");
        }else {
            map.put(ERROR_CODE, code);
            map.put(ERROR_MSSAGE, "添加商品失败");
        }
        return map;
    }

    @RequestMapping("/update")
    public Map<String ,Object> update(ProductDetail productDetail,ProductBrief brief){
        HashMap<String, Object> map = new HashMap<>();
        productDetail.setId(brief.getDetailId());
        brief.setId(productDetail.getProductId());
        Integer code = productService.updateProduct(brief, productDetail);
        if (code == CODE_OK) {
            map.put(ERROR_CODE, CODE_OK);
            map.put(ERROR_MSSAGE, "修改商品成功");
        }else {
            map.put(ERROR_CODE, code);
            map.put(ERROR_MSSAGE, "修改商品失败");
        }
        return map;
    }

    @RequestMapping("/remove")
    public Map<String ,Object> remove(@NotBlank  Integer id){
        HashMap<String, Object> map = new HashMap<>();
        if (id==null||id<=0){
            map.put(ERROR_CODE, CODE_PRODUCT_REMOVE_FAIL);
            map.put(ERROR_MSSAGE, "删除商品失败");
        }

        String loginRid = UserDetailUtil.getUser().getLoginRid();
        Integer[] ids = new Integer[1];
        ids[0]=id;
        Integer Code = productService.removeProduct(ids);
        if(loginRid.equals("ROLE_SELLER")||loginRid.equals("ROLE_ADMIN")){

            map.put(ERROR_CODE, Code);
            map.put(ERROR_MSSAGE, "删除商品成功");
        }else {
            map.put(ERROR_CODE, Code);
            map.put(ERROR_MSSAGE, "删除商品失败");
        }
        return  map;
    }
}
