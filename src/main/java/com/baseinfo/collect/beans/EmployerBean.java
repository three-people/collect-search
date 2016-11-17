package com.baseinfo.collect.beans;

public class EmployerBean {
    private Long id;

    private String type;

    private String name;

    private String address;

    private String chargeman;

    private String chargemanid;

    private String chargemanphone;

    private String safeman;

    private String safemanid;

    private String safemanphone;

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