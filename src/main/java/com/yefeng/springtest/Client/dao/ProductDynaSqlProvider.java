package com.yefeng.springtest.Client.dao;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ProductDynaSqlProvider {

//    public String findProductBriefByParams(Map<String ,Object> params) {
//        return new SQL() {{
//                SELECT("select p.id,p.name,p.price,p.picPath,u.username");
//                FROM("product as p");
//                LEFT_OUTER_JOIN("user as u on u.id=p.sellerId");
//                if(params.get("typeId")!=null){
//                    WHERE("p.typeId=#{typeId}");
//                }
//                if(params.get("keyWord")!=null){
//                    AND();
//                    WHERE("p.name like concat('%',#{keyWord},'%')");
//                }
//                if(params.get("priceMin")!=null){
//                    AND();
//                    WHERE("p.price >= #{priceMin}");
//                }
//                if(params.get("priceMax")!=null){
//                    AND();
//                    WHERE("p.price <= #{priceMin}");
//                }
//                if(params.get("pager")!=null){
//                    AND();
//                    WHERE("#{pager.firstLimitParam}, #{pager.rowsPerPage}");
//                }
//            }
//        }.toString();
//    }

    public String findProductBriefByParams(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("p.id", "p.name", "p.price","p.quantity", "u.username", "p.picPath");
                FROM("product AS p");
                LEFT_OUTER_JOIN("user AS u ON p.sellerId = u.id");
                if (params.get("typeId") != null) {
                    WHERE("p.typeId = #{typeId}");
                }
                if (params.get("keyWord") != null) {
                    AND();
                    WHERE("p.name LIKE CONCAT('%', #{keyWord}, '%')");
                }
                if (params.get("priceMin") != null) {
                    AND();
                    WHERE("p.price >= #{priceMin}");
                }
                if (params.get("priceMax") != null) {
                    AND();
                    WHERE("p.price <= #{priceMax}");
                }
                if (params.get("sellId") != null) {
                    AND();
                    WHERE("p.sellerId = #{sellId}");
                }
                if (params.get("pager") != null) {
                    LIMIT("#{pager.firstLimitParam}, #{pager.perPageRows}");
                }
            }
        }.toString();
    }
    public String findProductBriefCountByParams(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM("product AS p");
                LEFT_OUTER_JOIN("user AS u ON p.sellerId = u.id");
                if (params.get("typeId") != null) {
                    WHERE("p.typeId = #{typeId}");
                }
                if (params.get("keyWord") != null) {
                    AND();
                    WHERE("p.name LIKE CONCAT('%', #{keyWord}, '%')");
                }
                if (params.get("priceMin") != null) {
                    AND();
                    WHERE("p.price >= #{priceMin}");
                }
                if (params.get("sellId") != null) {
                    AND();
                    WHERE("p.sellerId = #{sellId}");
                }
                if (params.get("priceMax") != null) {
                    AND();
                    WHERE("p.price <= #{priceMax}");
                }
            }
        }.toString();
    }
}
