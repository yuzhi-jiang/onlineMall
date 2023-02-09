package com.yefeng.springtest.Client.entity;

public class Hot {
    private Integer id;
    private Integer typeId;
    private Integer hot;
//    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "hot{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", hot=" + hot +
                '}';
    }

    //    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
}
