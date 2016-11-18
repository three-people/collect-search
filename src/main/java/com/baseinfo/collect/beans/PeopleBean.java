package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

@Document(indexName = IndexConstants.PERSONINDEXNAME,type="fulltext")
public class PeopleBean {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Long id;
    //居民楼写字楼
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String type;
    //自用商用常住流动
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String subtype;
    //户主、出租人
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String hostname;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String hostid;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String hostphone;
    //人数
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Integer number;
    //租住人
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String lessee;
    //寄住人
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String stay;
    //从业人员
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String employee;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String expend;

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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype == null ? null : subtype.trim();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid == null ? null : hostid.trim();
    }

    public String getHostphone() {
        return hostphone;
    }

    public void setHostphone(String hostphone) {
        this.hostphone = hostphone == null ? null : hostphone.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee == null ? null : lessee.trim();
    }

    public String getStay() {
        return stay;
    }

    public void setStay(String stay) {
        this.stay = stay == null ? null : stay.trim();
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee == null ? null : employee.trim();
    }

    public String getExpend() {
        return expend;
    }

    public void setExpend(String expend) {
        this.expend = expend == null ? null : expend.trim();
    }
}