package com.yefeng.springtest.Client.controller;

import com.yefeng.springtest.Client.entity.*;
import com.yefeng.springtest.Client.service.AdvertiseService;
import com.yefeng.springtest.Client.service.OrderService;
import com.yefeng.springtest.Client.service.ProductService;
import com.yefeng.springtest.Client.service.ShoppingCarService;
import com.yefeng.springtest.util.UserDetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@Controller
public class MainController {
    private final Integer HOT_SALE_AMOUNT = 4;
    private final Integer ROWS_PER_PAGE = 24;
    @Autowired
    private ProductService productService;

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/daohang")
    public String daohang() {
        return "daohang";
    }

    @RequestMapping("/foot")
    public String foot() {
        return "foot";
    }


    @Resource
    private AdvertiseService advertiseService;

    @RequestMapping(value = {"/index", ""})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        List<ProductType> hotSellProducts = productService.getHotSellProducts();
        List<Advertise> advertiseList = advertiseService.getAllAdsByTypeId(1);

        view.addObject("hotSellProducts", hotSellProducts);
        view.addObject("advertiseList", advertiseList);
        view.setViewName("index");
        System.out.println(hotSellProducts);
        return view;
    }

    @RequestMapping("/productdetail")
    public ModelAndView productdetail(Integer id) {
        ModelAndView view = new ModelAndView();
        view.addObject("id", id);
        view.setViewName("productDetail");
        System.out.println(id);
        return view;

    }

    @RequestMapping("/productlist")
    public ModelAndView productList(Long typeId, String keyWord,
                                    @Min(1) Integer curPage,
                                    Integer priceMin,
                                    Integer priceMax) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("typeId", typeId);
        mv.addObject("keyWord", keyWord);
        mv.addObject("curPage", curPage);
        mv.addObject("rowsPerPage", ROWS_PER_PAGE);
        mv.addObject("priceMin", priceMin);
        mv.addObject("priceMax", priceMax);
        mv.setViewName("productList");
        return mv;
    }


    @RequestMapping("/orderConfirm")
    public ModelAndView orderConfirm(ProductInfos infos, Boolean isFromShoppingCar) {

        ModelAndView view = null;
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("userDetails:" + userDetails.toString());
            System.out.println("infos:");
            ShoppingCarItem[] shoppingCarItems = infos.getShoppingCarItems();
            for (ShoppingCarItem shoppingCarItem : shoppingCarItems) {
                System.out.println(shoppingCarItem);
            }

            System.out.println("isFromShoppingCar:" + isFromShoppingCar);
            view = new ModelAndView();
            view.addObject("isFromShoppingCar", isFromShoppingCar);
            view.addObject("productItems", infos.getShoppingCarItems());
            view.setViewName("orderConfirm");
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            view.setViewName("index");
            return view;
        }

    }


    @Resource
    private ShoppingCarService shoppingCarServer;

    @RequestMapping("/shoppingcar")
    public ModelAndView shoppingCar() {
        ModelAndView view = new ModelAndView();
        UserDetail2 user = (UserDetail2) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user != null) {
            List<ShoppingCarItem> items = shoppingCarServer.findShoppingCarItemsByUserId(user.getId());
            items.forEach(item -> {
                System.out.println(item.toString());
            });

            if (items != null) {
                ShoppingCarItem[] shoppingCarItems = items.toArray(new ShoppingCarItem[]{});
                view.addObject("shoppingCarItems", shoppingCarItems);
            } else {
                ShoppingCarItem[] shoppingCarItems = new ShoppingCarItem[]{};
                view.addObject("shoppingCarItems", shoppingCarItems);
            }
        }
        view.setViewName("shoppingCar");
        return view;
    }

    @Resource
    private OrderService orderService;

    @RequestMapping("myorder")
    public ModelAndView myOrder(@Min(1) Integer curPage, @NotBlank String role) {
        ModelAndView view = new ModelAndView();
        Pager pager = new Pager();
        pager.setPerPageRows(ROWS_PER_PAGE);
        pager.setCurPage(curPage);

        UserDetail2 user = UserDetailUtil.getUser();
        if(role.equals(ROLE_BUYER)){//买 家
            try {
                OrderInfos myOrders =orderService.findMyOrdersByUidAndPager(user.getId(),pager);
                Integer count = orderService.findOrderInfosCountByUserId(user.getId());
                Integer totalPage = (count + ROWS_PER_PAGE - 1) / ROWS_PER_PAGE;
                view.addObject("curPage", curPage);
                view.addObject("rowsPerPage", ROWS_PER_PAGE);
                view.addObject("totalPage", totalPage);
                view.addObject("orderInfos", myOrders.getOrders());
                view.setViewName("myOrder");
            } catch (Exception e) {
                e.printStackTrace();
                view.addObject("curPage", curPage);
                view.addObject("rowsPerPage", ROWS_PER_PAGE);
                view.addObject("totalPage", "");
                view.addObject("orderInfos", null);
            }

        }else if(role.equals(ROLE_SELLER)){//卖家

            try {
                List<SellerOrderInfo> sellerOrderInfos = orderService.findMyOrdersBySellerIdAndPager(user.getId(), pager);


                Long count = orderService.findOrderInfosCountBySellerId(user.getId());
                Long totalPage = (count + ROWS_PER_PAGE - 1) / ROWS_PER_PAGE;
                view.addObject("curPage", curPage);
                view.addObject("rowsPerPage", ROWS_PER_PAGE);
                view.addObject("totalPage", totalPage);
                view.addObject("sellerOrderInfos", sellerOrderInfos);
                view.setViewName("sellerOrder");
            } catch (Exception e) {
                e.printStackTrace();
                view.addObject("curPage", curPage);
                view.addObject("rowsPerPage", ROWS_PER_PAGE);
                view.addObject("totalPage", "");
                view.addObject("orderInfos", null);
            }
        }




        return view;
    }

    @RequestMapping("/businessRegister")
    public String businessRegister() {
        return "businessRegister";
    }

    @RequestMapping("/addproduct")
    public String addproduct(){
        return "addProduct";
    }

    @RequestMapping("/myproduct")
    public ModelAndView myProduct(Integer curPage) {
        ModelAndView view = new ModelAndView();
        Integer rowsPerPage = 24;
        view.setViewName("myProduct");
        Map<String, Object> params = new HashMap<>();
        UserDetail2 user = UserDetailUtil.getUser();
        Long sellId = user.getId();//当前用户id
        params.put("sellId", sellId);
        Pager pager = new Pager();
        pager.setCurPage(curPage);
        pager.setPerPageRows(rowsPerPage);
        params.put("pager", pager);
        List<ProductBrief> productList = productService.findProductBriefByParams(params);

        //符合类型的商品有多少个，方便分页
        Integer count = productService.findProductBriefCountByParams(params);

        System.out.println("有" + count + "个");
        if (productList != null && count >= 0) {
            Integer totalPage = (count + rowsPerPage - 1) / rowsPerPage;
            System.out.println("有" + totalPage + "页");
            view.addObject("keyWord", "");

            view.addObject("productBriefs", productList);
            view.addObject("curPage", curPage);
            view.addObject("rowsPerPage", rowsPerPage);
            view.addObject("totalPage", totalPage);
        } else {
            view.addObject("keyWord", "");

            view.addObject("productList", new ArrayList<Product>());
            view.addObject("curPage", curPage);
            view.addObject("rowsPerPage", rowsPerPage);
            view.addObject("totalPage", 0);
        }


        return view;
    }



    @RequestMapping("/productUpdate")
    public ModelAndView productUpdate(Long productId,Integer curPage){
        ModelAndView view = new ModelAndView();
        view.setViewName("updateProduct");
        ProductDetail detail = productService.findProductDetailByProductId(productId);

        view.addObject("productId",productId);
        view.addObject("picPath",detail.getPicPath());
        view.addObject("detailId",detail.getId());
        view.addObject("productName",detail.getProductName());
        view.addObject("price",detail.getPrice());
        view.addObject("quantity",detail.getQuantity());
        view.addObject("typeId",detail.getTypeId());
        view.addObject("fullName",detail.getFullName());
        view.addObject("brandName",detail.getBrandName());
        view.addObject("shelfLife",detail.getShelfLife());
        view.addObject("producer",detail.getProducer());
        view.addObject("producerAddress",detail.getProducerAddress());
        view.addObject("producerTel",detail.getProducerTel());
        view.addObject("description",detail.getDescription());
        view.addObject("curPage",curPage);

        return view;
    }
}
