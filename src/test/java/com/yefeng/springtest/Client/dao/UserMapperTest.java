package com.yefeng.springtest.Client.dao;

import com.yefeng.springtest.Client.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

class UserMapperTest {
    @Resource
    private UserMapper mapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Lazy
    @Autowired
    private UserService service;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void loginTest(){

//        User user = mapper.loginUser("123412", "$10$GBHflexBOgdLWjSrX9qPVep64t/fBD73uTr9/RbEwiFiOEvqhKxrG");
//        System.out.println(user);
    }

}