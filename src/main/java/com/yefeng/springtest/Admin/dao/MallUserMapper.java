package com.yefeng.springtest.Admin.dao;

import com.yefeng.springtest.Admin.entity.MallUser;
import com.yefeng.springtest.Admin.entity.Pager;
import com.yefeng.springtest.Client.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MallUserMapper {


//    分页查找用户
    @Select("select * from user as u\n" +
            "left join user_role as ur on ur.userId=u.id\n" +
            "left join role as r on r.id=ur.roleId limit #{pager.firstLimitParam}, #{pager.perPageRows}")
    public List<MallUser> findMallUserByPage(@Param("pager")Pager pager);

    //    查找全部用户
    @Select("select * from user as u\n" +
            "left join user_role as ur on ur.userId=u.id\n" +
            "left join role as r on r.id=ur.roleId")
    public List<MallUser> findAllUser();

    @Delete("<script>" +
            "delete from user where id in" +
            "<foreach collection='ids' item='id' index='index' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    public void deleteMallUserByIds(int[] ids);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into user(username,password,gender,phoneNUm) values(#{username},#{password},#{gender},#{phoneNum},#{address},now(),#{mail)")
    public Integer insterUser(MallUser user);

    @Insert("insert into user_role values(null,#{uid},#{rid})")
    public void insertUserRole(@Param("uid") Long uid, @Param("rid") Integer rid);
}
