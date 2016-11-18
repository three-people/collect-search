package com.baseinfo.collect.beans;

import com.baseinfo.collect.common.IndexConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

@Document(indexName = IndexConstants.CAMERAINDEXNAME,type="fulltext")
public class CameraBean {
    @Id
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Long id;
    //设备编号（自定义命名规则）
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String deviceid;
    //分类：派出所建设;办事处建设;单位自建;
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String type;
    //所属派出所
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String policestation;
    //监控地点
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String localname;
    //设备类型
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String devicetype;
    //监控摄像方向
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String direction;
    //数量
    @Field(index = FieldIndex.not_analyzed, store = true)
    private Integer count;
    //扩展字段
    @Field(index = FieldIndex.not_analyzed, store = true)
    private String expend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPolicestation() {
        return policestation;
    }

    public void setPolicestation(String policestation) {
        this.policestation = policestation == null ? null : policestation.trim();
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname == null ? null : localname.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getExpend() {
        return expend;
    }

    public void setExpend(String expend) {
        this.expend = expend;
    }
}