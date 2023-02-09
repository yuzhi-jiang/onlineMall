package com.yefeng.springtest.Admin.service;

import com.yefeng.springtest.Admin.entity.MallUser;
import com.yefeng.springtest.Admin.entity.Pager;

import java.util.List;

public interface MallUserService {

    public Integer deleteMallUsersByid(int[] ids);
    public Integer addMallUser(MallUser mallUser);
    public List<MallUser> getUserByPage(Pager pager);
    public List<MallUser> getAllUserByPage();


}
