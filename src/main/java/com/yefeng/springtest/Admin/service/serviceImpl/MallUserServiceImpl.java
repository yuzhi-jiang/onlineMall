package com.yefeng.springtest.Admin.service.serviceImpl;

import com.yefeng.springtest.Admin.dao.MallMapper;
import com.yefeng.springtest.Admin.dao.MallUserMapper;

import com.yefeng.springtest.Admin.entity.MallUser;
import com.yefeng.springtest.Admin.entity.Pager;
import com.yefeng.springtest.Admin.service.MallUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MallUserServiceImpl implements MallUserService {
    @Resource
    private MallUserMapper mallUserMapper;

    @Override
    public Integer deleteMallUsersByid(int[] ids) {
        return null;
    }

    @Override
    public Integer addMallUser(MallUser mallUser) {
        return null;
    }

    @Override
    public List<MallUser> getUserByPage(Pager pager) {
        return mallUserMapper.findMallUserByPage(pager);
    }

    @Override
    public List<MallUser> getAllUserByPage() {
        return mallUserMapper.findAllUser();
    }

}
