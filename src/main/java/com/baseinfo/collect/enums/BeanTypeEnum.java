package com.baseinfo.collect.enums;

/**
 * Created by Walker on 2016/11/20.
 */
public enum BeanTypeEnum {

    PEOPLE(1, new String[]{"", ""}),

    HOUSE(2, new String[]{"", ""}),

    EMPLOYER(3, new String[]{"", ""}),

    PLACE(4, new String[]{"", ""}),

    CAMERA(5, new String[]{"设备编号", "所属派出所", "分类", "监控地点", "设备类型", "设备朝向", "数量", "备注"});

    BeanTypeEnum(int type, String[] value) {
        this.type = type;
        this.value = value;
    }

    public static BeanTypeEnum getEnum(int type) {
        for (BeanTypeEnum item : BeanTypeEnum.values()) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }

    private String[] value;
    private int type;

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
