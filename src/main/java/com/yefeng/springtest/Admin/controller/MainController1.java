package com.yefeng.springtest.Admin.controller;

import com.yefeng.springtest.Admin.dao.MallProductMapper;
import com.yefeng.springtest.Admin.entity.*;
import com.yefeng.springtest.Admin.service.AdvertiseService;
import com.yefeng.springtest.Admin.service.MallProductService;
import com.yefeng.springtest.Admin.service.MallService;
import com.yefeng.springtest.Admin.service.MallUserService;

import com.yefeng.springtest.util.UserDetailUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class MainController1 {
    private final static String ADMIN_PATH = "admin";

    @RequestMapping("")
    public String Main() {
        String rid = UserDetailUtil.getUser().getLoginRid();
        if (rid.equals("ROLE_ADMIN")) {
            return "/admin/index";
        }

        return "/login";
    }

    @Resource
    private AdvertiseService advertiseService;

    @RequestMapping("/advertising")
    public ModelAndView advertise() {
        ModelAndView view = new ModelAndView();
        view.setViewName(ADMIN_PATH + "/" + "advertising");
        List<Advertise> Ads_list = advertiseService.getAllAds();

        view.addObject("Ads_list", Ads_list);
        view.addObject("Ads_count", Ads_list.size());

        System.out.println(view);

        return view;
    }

//    @RequestMapping("/{parm}")
//    public String parm(@PathVariable(name = "parm")String parm){
//        System.out.println(parm);
//        String path = ADMIN_PATH + '/' + parm;
//        return path;
//    }


    @RequestMapping("login")
    public String login() {

        return "admin/login";
    }


    @RequestMapping("index")
    public String index() {

            String rid = UserDetailUtil.getUser().getLoginRid();
//            if (rid.equals("ROLE_ADMIN")) {
                return "admin/index";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "../login";

    }

    @Resource
    private MallService mallService;

    @RequestMapping("home")
    public ModelAndView home() {
        ModelAndView view = new ModelAndView("admin/home");

        Long mallUserCount = mallService.getMallUserCount();

        Long orderCount = mallService.getMallOrderCount();
        Long o_pay = mallService.getOrderAmountByStatus(0);
        Long o_ship = mallService.getOrderAmountByStatus(1);
        Long o_success = mallService.getOrderAmountByStatus(2);

        Double totalTransaction = mallService.getTotalTransaction();


        Long productAmount = mallService.getProductAmount();
        Long p_Online = mallService.getProducAmounttByStatus(1);
        Long p_Offline = mallService.getProducAmounttByStatus(0);


        view.addObject("mallUserCount", mallUserCount);
        view.addObject("orderCount", orderCount);
        view.addObject("o_pay", o_pay);
        view.addObject("o_ship", o_ship);
        view.addObject("o_success", o_success);


        view.addObject("totalTransaction", totalTransaction);

        view.addObject("productAmount", productAmount);
        view.addObject("p_Online", p_Online);
        view.addObject("p_Offline", p_Offline);


        return view;
    }


    @Resource
    private MallProductService productService;

    @RequestMapping("products_List")
    public ModelAndView products_List(Integer typeId) {
        ModelAndView view = new ModelAndView("admin/products_List");
        List<MallProduct> productList = null;
        if (typeId != null && typeId > 0) {
            productList = productService.getAllProductByTypeId(typeId);
        } else {
            productList = productService.getAllProduct();
        }
        view.addObject("productList", productList);
        view.addObject("productAmount", productList.size());


//        System.out.println(view.toString());


        return view;
    }

    @RequestMapping("brand_Manage")
    public String brand_Manage() {
        return "admin/brand_Manage";
    }

    @RequestMapping("category_Manage")
    public String category_Manage() {
        return "admin/category_Manage";
    }

    @RequestMapping("product-category-add")
    public String productCategoryadd() {
        return "admin/product-category-add";
    }

    @RequestMapping("member-add")
    public String memberAdd() {
        return "admin/member-add";
    }

    @RequestMapping("systems")
    public String systemts() {
        return "admin/systems";
    }

    @RequestMapping("order_Chart")
    public String order_Chart() {
        return "admin/order_Chart";
    }


    /*
        用户列表，

     */
    @Resource
    private MallUserService mallUserService;

    @RequestMapping("user_list")
    public ModelAndView user_list(Integer curPage, Integer rowsPerPage) {
        ModelAndView view = new ModelAndView("admin/user_list");

        Long mallUserCount = mallService.getMallUserCount();
        if (curPage != null && rowsPerPage != null) {
            Pager pager = new Pager();
            pager.setCurPage(curPage);
            pager.setPerPageRows(rowsPerPage);

            List<MallUser> userList = mallUserService.getUserByPage(pager);

            view.addObject("userAmount", mallUserCount);
            view.addObject("userList", userList);
        } else {
            List<MallUser> userList = mallUserService.getAllUserByPage();

            view.addObject("userAmount", mallUserCount);
            view.addObject("userList", userList);
        }

        return view;
    }


    /*
        广告分类管理
     */
    @RequestMapping("sort_ads")
    public ModelAndView sort_ads() {
        ModelAndView view = new ModelAndView();
        view.setViewName("admin/sort_ads");

        List<AdvertiseType> adtype = advertiseService.getAllAdType();

        view.addObject("AdvertiseType_list", adtype);


        return view;
    }


    @RequestMapping("transaction")
    public String transaction() {
        return "admin/transaction";
    }

    @RequestMapping("orderform")
    public String orderform() {
        return "admin/orderform";
    }

    @RequestMapping("amounts")
    public String amounts() {
        return "admin/amounts";
    }

    @RequestMapping("order_handling")
    public String order_handling() {
        return "admin/order_handling";
    }

    @RequestMapping("admin_Competence")
    public String admin_Competence() {
        return "admin/admin_Competence";
    }

    @RequestMapping("administrator")
    public String administrator() {
        return "admin/administrator";
    }

    @RequestMapping("admin_info")
    public String admin_info() {
        return "admin/admin_info";
    }

}
