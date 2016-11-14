package com.baseinfo.collect.beans;

import java.util.Date;

/**
 * 用户实体Bean
 */
public class UserBean {

    private long id;

    private String uname;

    private String pwd;

    private String mobile;

    private String email;

    private String realName;

    private int sex;

    private int age;

    private int role;

    private int status;

    private int ufrom;

    private String ip;

    private String departmentName;

    private Date addtime;

    private Date updatetime;

    public long getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getPwd() {
        return pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getRealName() {
        return realName;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public int getRole() {
        return role;
    }

    public int getStatus() {
        return status;
    }

    public int getUfrom() {
        return ufrom;
    }

    public String getIp() {
        return ip;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Date getAddtime() {
        return addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUfrom(int ufrom) {
        this.ufrom = ufrom;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
