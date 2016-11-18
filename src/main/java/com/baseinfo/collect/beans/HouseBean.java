package com.baseinfo.collect.beans;

public class HouseBean {
    private Long id;
    //居民楼写字楼
    private String type;
    //平房楼房
    private String subtype;
    //地址
    private String location;
    //产权人
    private String host;
    //房主户主
    private String owner;

    private String ownerid;

    private String ownerphone;
    //单元
    private String unit;
    //楼层
    private Integer floor;
    //门牌号
    private String doornumber;
    //从业人员
    private String employee;

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
}