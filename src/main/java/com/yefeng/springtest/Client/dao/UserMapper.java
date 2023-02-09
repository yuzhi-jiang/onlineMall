package com.yefeng.springtest.Client.dao;

import com.yefeng.springtest.Client.entity.MyUserDetail;
import com.yefeng.springtest.Client.entity.Role;
import com.yefeng.springtest.Client.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserMapper {
    //注册用户
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into user values(null,#{name},#{password},#{gender},#{phone},#{address})")
    public Integer insterUser(User user);

    @Insert("insert into user_role values(null,#{uid},#{rid})")
    public void insertUserRole(@Param("uid") Long uid, @Param("rid") Integer rid);


    //登录
    @Select("select * from user where username=#{name} LIMIT 1")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER,id = true),
            @Result(column = "username",property = "name"),
            @Result(column = "password",property = "password"),
            @Result(column = "gender",property = "gender" ,javaType =Boolean.class),
            @Result(column = "phoneNum",property = "phone"),
            @Result(column = "address",property = "address"),
    })
    public User loginUser(@Param("name") String name);


    @Select("select * from user where username=#{name} limit 1")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER,id = true),
            @Result(column = "username",property = "name"),
            @Result(column = "password",property = "password"),
            @Result(column = "gender",property = "gender" ,javaType =Boolean.class),
            @Result(column = "phoneNum",property = "phone"),
            @Result(column = "address",property = "address"),
            @Result(column = "id",property = "roles",
                    many = @Many(select = "com.yefeng.springtest.Client.dao.UserMapper.findRolesByUid",
                                fetchType= FetchType.EAGER
                                )
                    )
    })
    public MyUserDetail findUserByUsername(@Param("name") String  userName);

    @Select("select id,username as name,password,gender,phoneNum as phone,address")
    public User findUserByUserId(@Param("uid") Long uid);


    @Select("select * from role where id in(select roleId from user_role where userId=#{uid})")
    public List<Role> findRolesByUid(@Param("uid") Long uid);


}
