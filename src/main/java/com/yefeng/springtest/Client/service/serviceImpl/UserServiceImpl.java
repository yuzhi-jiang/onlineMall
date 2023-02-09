package com.yefeng.springtest.Client.service.serviceImpl;

import com.yefeng.springtest.Client.dao.UserMapper;
import com.yefeng.springtest.Client.entity.MyUserDetail;
import com.yefeng.springtest.Client.entity.Role;
import com.yefeng.springtest.Client.entity.User;
import com.yefeng.springtest.Client.entity.UserDetail2;
import com.yefeng.springtest.Client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.yefeng.springtest.util.Constant.*;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Lazy
    @Autowired
    private UserService service;

    @Override
    public Integer userRegister(User user) {
        String password = user.getPassword();
        //加密密码
        String bcryPasswrod = bCryptPasswordEncoder.encode(password);
        user.setPassword(bcryPasswrod);

        try {
            service.userRegisterTx(user);
            return CODE_OK;
        }catch (DuplicateKeyException e){
            return Code_USER_EXIST;
        }
        catch (Exception e) {
            e.printStackTrace();
            return CODE_USER_ERROR;
        }

    }

    @Override
    @Transactional
    public void userRegisterTx(User user) {
        mapper.insterUser(user);
        mapper.insertUserRole(user.getId(),user.getRid());
    }

    @Override
    public Integer addUserRole(User user){
        try {
            List<Role> roles = mapper.findRolesByUid(user.getId());
            int index = roles.indexOf(user.getRid());
            //权限里面没有
            if(index==-1){
                mapper.insertUserRole(user.getId(),user.getRid());
                return CODE_OK;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return USER_ROLE_ADD_FALSE;

    }

    @Override
    public Integer userLogin(String name, String password) {
        System.out.println("psd:"+bCryptPasswordEncoder.encode(password));
        User user = mapper.loginUser(name);
//        System.out.println(user);
//        System.out.println("bCryptPasswordEncoder.matches("+bCryptPasswordEncoder.encode(password)+","+password+")=="+bCryptPasswordEncoder.matches(bCryptPasswordEncoder.encode(password),password));
//        System.out.println("bCryptPasswordEncoder.matches(+"+password+","+user.getPassword()+")=="+bCryptPasswordEncoder.matches(password,user.getPassword()));

        if(user!=null&&bCryptPasswordEncoder.matches(password,user.getPassword())){
            return CODE_OK;
        }else{
            return CODE_USER_ERROR;
        }
    }

    @Override
    public Integer userLogin(String name, String password, Integer rid) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetail user = mapper.findUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        System.out.println(user);
        UserDetail2 userDetail2 = new UserDetail2(user.getName(), user.getPassword(), true, true, true,true, user.getAuthorities(), user.getId(), user.getName(), user.getPassword(), user.getPhone(), user.isGender(), user.getAddress(), user.getRoles());
        //        UserDetail2 userDetail2 = new UserDetail2(user.getUsername(),user.getPassword(),user.getAuthorities());
//        userDetail2.setName(user.getName());
//        userDetail2.setAddress(user.getAddress());
//        userDetail2.setGender(user.isGender());
//        userDetail2.setId(user.getId());
//        userDetail2.setPhone(user.getPhone());
//        userDetail2.setRoles(user.getRoles());
        return userDetail2;
    }
}
