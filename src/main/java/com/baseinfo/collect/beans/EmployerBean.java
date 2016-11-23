package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = IndexConstants.EMPLOYERINDEXNAME,type="fulltext")
public class EmployerBean {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Long id;
    @Field(index = FieldIndex.not_analyzed, store = true)
    //类型，重点单位，一级单位，二级单位，一般单位
    private String type;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String name;
    @Field(type = FieldType.String,index = FieldIndex.analyzed, analyzer = IndexConstants.AnalyserName ,store = true,searchAnalyzer = IndexConstants.AnalyserName)
    private String address;
    //负责人姓名
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String chargeman;
    //身份证号
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String chargemanid;
    //电话
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String chargemanphone;
    //内保负责人
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String safeman;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String safemanid;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String safemanphone;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String extend;

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

    public String getChargeman() {
        return chargeman;
    }

    public void setChargeman(String chargeman) {
        this.chargeman = chargeman == null ? null : chargeman.trim();
    }

    public String getChargemanid() {
        return chargemanid;
    }

    public void setChargemanid(String chargemanid) {
        this.chargemanid = chargemanid == null ? null : chargemanid.trim();
    }

    public String getChargemanphone() {
        return chargemanphone;
    }

    public void setChargemanphone(String chargemanphone) {
        this.chargemanphone = chargemanphone == null ? null : chargemanphone.trim();
    }

    public String getSafeman() {
        return safeman;
    }

    public void setSafeman(String safeman) {
        this.safeman = safeman == null ? null : safeman.trim();
    }

    public String getSafemanid() {
        return safemanid;
    }

    public void setSafemanid(String safemanid) {
        this.safemanid = safemanid == null ? null : safemanid.trim();
    }

    public String getSafemanphone() {
        return safemanphone;
    }

    public void setSafemanphone(String safemanphone) {
        this.safemanphone = safemanphone == null ? null : safemanphone.trim();
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }
}