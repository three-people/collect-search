package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = IndexConstants.HOUSEINDEXNAME,type="fulltext")
public class HouseBean {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Long id;
    //居民楼写字楼
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String type;
    //平房楼房
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String subtype;
    //地址
    @Field(type = FieldType.String,index = FieldIndex.analyzed, analyzer = IndexConstants.AnalyserName ,store = true,searchAnalyzer = IndexConstants.AnalyserName)
    private String location;
    //产权人
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String host;
    //房主户主
    private String owner;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String ownerid;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String ownerphone;
    //单元
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String unit;
    //楼层
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Integer floor;
    //门牌号
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String doornumber;
    //从业人员
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String employee;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String expend;

    @Field(index = FieldIndex.not_analyzed, store = true)
    private String policeid;

    @Field(index = FieldIndex.not_analyzed, store = true)
    private String policename;

    @Field(index = FieldIndex.no, store = true)
    private String policearea;

    @Field(index = FieldIndex.not_analyzed,store = true)
    private String neighbor;

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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype == null ? null : subtype.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid == null ? null : ownerid.trim();
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone == null ? null : ownerphone.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDoornumber() {
        return doornumber;
    }

    public void setDoornumber(String doornumber) {
        this.doornumber = doornumber == null ? null : doornumber.trim();
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

    public String getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }
}