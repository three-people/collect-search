package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = IndexConstants.PLACEINDEXNAME,type="fulltext")
public class PlaceBean {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Long id;
    //餐饮、教学等
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String type;
    //名称
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String name;
    @Field(type = FieldType.String,index = FieldIndex.analyzed, analyzer = IndexConstants.AnalyserName ,store = true,searchAnalyzer = IndexConstants.AnalyserName)
    private String address;
    //面积
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String area;
    //出租人
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String lessor;
    //出租人身份证
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String lessorid;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String lessorphone;
    //租住人
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String lessee;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String lesseeid;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String lesseephone;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String extend;

    @Field(index = FieldIndex.not_analyzed, store = true)
    private String policeid;

    @Field(index = FieldIndex.not_analyzed, store = true)
    private String policename;

    @Field(index = FieldIndex.not_analyzed, store = true)
    private String policearea;

    @Field(index = FieldIndex.not_analyzed, store = true)
    private Date addtime;

    @Field(index = FieldIndex.not_analyzed, store = true)
    private Date updatetime;

    public Date getAddtime() {
        return addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor == null ? null : lessor.trim();
    }

    public String getLessorid() {
        return lessorid;
    }

    public void setLessorid(String lessorid) {
        this.lessorid = lessorid == null ? null : lessorid.trim();
    }

    public String getLessorphone() {
        return lessorphone;
    }

    public void setLessorphone(String lessorphone) {
        this.lessorphone = lessorphone == null ? null : lessorphone.trim();
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee == null ? null : lessee.trim();
    }

    public String getLesseeid() {
        return lesseeid;
    }

    public void setLesseeid(String lesseeid) {
        this.lesseeid = lesseeid == null ? null : lesseeid.trim();
    }

    public String getLesseephone() {
        return lesseephone;
    }

    public void setLesseephone(String lesseephone) {
        this.lesseephone = lesseephone == null ? null : lesseephone.trim();
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }

    public String getPoliceid() {
        return policeid;
    }

    public String getPolicename() {
        return policename;
    }

    public String getPolicearea() {
        return policearea;
    }

    public void setPoliceid(String policeid) {
        this.policeid = policeid;
    }

    public void setPolicename(String policename) {
        this.policename = policename;
    }

    public void setPolicearea(String policearea) {
        this.policearea = policearea;
    }
}