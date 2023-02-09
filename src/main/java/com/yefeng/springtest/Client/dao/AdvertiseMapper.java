package com.yefeng.springtest.Client.dao;

import com.yefeng.springtest.Client.entity.Advertise;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("ClientAdvertiseMapper")
@Mapper
public interface AdvertiseMapper {

    //获取全部广告
    @Select("select * from advertise as ad\n" +
            "left join advertisetype  as adt on adt.id=ad.typeid")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "typeId",property = "typeId"),
            @Result(column = "sizex",property = "sizex"),
            @Result(column = "sizey",property = "sizey"),
            @Result(column = "link",property = "link"),
            @Result(column = "status",property = "status"),
            @Result(column = "time",property = "time"),
            @Result(column = "typeId",property = "type" ,one = @One(select = "com.yefeng.springtest.Admin.dao.AdvertiseMapper.findAdsNamtypeeById",fetchType = FetchType.EAGER)),

    })
    public List<Advertise> findAllAds();

    //根据广告类型id获取广告
    @Select("select * from advertise as ad\n" +
            "left join advertisetype  as adt on adt.id=ad.typeid\n" +
            "where adt.id=#{id} limit 3")
    public List<Advertise> findAllAdsByTypeId(@Param("id") int id);
}
