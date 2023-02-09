package com.yefeng.springtest.Admin.dao;

import com.yefeng.springtest.Admin.entity.AdInfo;
import com.yefeng.springtest.Admin.entity.Advertise;
import com.yefeng.springtest.Admin.entity.AdvertiseType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Mapper
@Repository("AdminAdvertiseMapper")
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
            "where adt.id=#{id}")
    public List<Advertise> findAllAdsByTypeId(@Param("id") int id);

    //根据广告类型id获取广告类型中文名称
    @Select("select text from advertisetype where id=#{id}")
    public String findAdsNamtypeeById(@Param("id") int id);

    //.插入一条广告
    @Insert("insert into advertise values(null,#{typeId},#{sizex},#{sizey},#{link},#{picPath},now(),#{status})")
    public void insertAdvertise(Advertise advertise);


    //添加广告分类
    @Insert("nsert into advertisetype values(null,#{name},#{text},#{description},#{status})")
    public void insertAdType(AdvertiseType advertiseType);

    //查询广告分类
    @Select("select * from advertisetype")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "text",property = "text"),
            @Result(column = "description",property = "description"),
            @Result(column = "status",property = "status"),
            @Result(column = "time",property = "time"),
            @Result(column = "id",property = "number",one = @One(select = "com.yefeng.springtest.Admin.dao.AdvertiseMapper.findAdAmountByTypeId",fetchType = FetchType.EAGER))
    })
    public List<AdvertiseType> findAllAdType();

    @Select("select count(*) from advertise where typeid = #{tid}")
    public Integer findAdAmountByTypeId(@Param("tid")Integer tid);

//10.根据类型名找到广告id

    @Select("select id from advertisetype where name =#{name}")
    public Integer findAdIdByName(@Param("name")String name);
}
