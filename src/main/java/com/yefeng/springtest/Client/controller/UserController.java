package com.yefeng.springtest.Client.controller;

import com.yefeng.springtest.Client.entity.User;

import com.yefeng.springtest.Client.entity.UserDetail2;
import com.yefeng.springtest.Client.service.UserService;
import com.yefeng.springtest.util.UserDetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@RestController
@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
public class UserController {

    @Autowired
    private UserService service;


    @RequestMapping("/register")
    public Map<String, Object> userRegister(@Validated User user, BindingResult bindingResult) {
        HashMap<String, Object> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            map.put(ERROR_CODE, "-1");
            map.put(ERROR_MSSAGE, "参数有误");
            System.out.println(map);
            return map;
        }
        Integer Code = service.userRegister(user);
        if (CODE_OK == Code) {
            map.put(ERROR_CODE, Code);
            map.put(ERROR_MSSAGE, "注册成功");
        } else if (CODE_USER_ERROR == Code) {
            map.put(ERROR_CODE, Code);
            map.put(ERROR_MSSAGE, "注册失败");
        } else if (Code_USER_EXIST == Code) {
            map.put(ERROR_CODE, Code);
            map.put(ERROR_MSSAGE, "注册失败,用户名已经存在");
        } else {
            map.put(ERROR_CODE, "-2");
            map.put(ERROR_MSSAGE, "注册失败");
        }


        System.out.println(map);
        return map;
    }


    @RequestMapping("/login")
    public Map<String, Object> userLogin(String name,String password,Integer rid) {
        System.out.println("name:"+name+" password:"+password);
        HashMap<String, Object> map = new HashMap<>();
        if(name!=null&&password!=null){
            Integer Code = service.userLogin(name, password);
            if(Code==CODE_OK){
                map.put(ERROR_CODE, Code);
                map.put(ERROR_MSSAGE, "登录成功");
                map.put("rid",rid);
            }else {
                map.put(ERROR_CODE, Code);
                map.put(ERROR_MSSAGE, "账号或密码错误");
            }
        }
        System.out.println(map);
        return map;
    }




    @RequestMapping("/becomeBusiness")
    public Map<String, Object> userRoleAdd() {
        HashMap<String, Object> map = new HashMap<>();

        UserDetail2 userDetail = UserDetailUtil.getUser();

        User user = new User();
        user.setId(userDetail.getId());
        user.setRid(2);
        Integer Code = service.addUserRole(user);
        if(Code==CODE_OK){
            map.put(ERROR_CODE,Code);
            map.put(ERROR_MSSAGE,"注册商家成功权限");
        }

        System.out.println(map);
        return map;
    }
}
