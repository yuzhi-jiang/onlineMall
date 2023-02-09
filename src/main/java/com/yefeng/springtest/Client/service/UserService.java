package com.yefeng.springtest.Client.service;

import com.yefeng.springtest.Client.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public Integer userRegister(User user);
    public void userRegisterTx(User user);

    Integer addUserRole(User user);

    public Integer userLogin(String name, String password);
    public Integer userLogin(String name, String password,Integer rid);
}
