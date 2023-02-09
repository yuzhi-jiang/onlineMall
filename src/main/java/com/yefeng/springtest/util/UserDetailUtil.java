package com.yefeng.springtest.util;

import com.yefeng.springtest.Client.entity.UserDetail2;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetailUtil {
    public static UserDetail2 getUser(){

        UserDetail2 user = null;
        try {
            user = (UserDetail2) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
}
