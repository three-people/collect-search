package com.baseinfo.collect.beans;

public class PlaceBean {
    private Long id;

    private String type;

    private String name;

    private String address;

    private String area;

    private String lessor;

    private String lessorid;

    private String lessorphone;

    private String lessee;

    private String lesseeid;

    private String lesseephone;

    private String extend;

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
}