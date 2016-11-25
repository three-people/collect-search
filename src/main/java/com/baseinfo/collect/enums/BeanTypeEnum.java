package com.baseinfo.collect.enums;

/**
 * Created by Walker on 2016/11/20.
 */
public enum BeanTypeEnum {

    PEOPLE("people", "实有人口", new String[]{"楼宇功能", "类型", "户主/出租人", "身份证号码", "联系方式",
            "人数", "租住人", "寄住人", "从业人员", "备注"},
            new int[]{50,50,50,50,50,5,50,50,200,200}),

    HOUSE("house", "实有房屋", new String[]{"楼宇功能", "类别", "产权人", "户主/房主", "身份证号码", "联系方式", "从业人员",
            "地址", "单元", "楼层", "门牌号", "备注"},
            new int[]{50,50,50,50,50, 50,50,200,50,3, 200,200}),

    EMPLOYER("employer", "实有单位", new String[]{"单位级别", "名称", "负责人", "身份证号码", "联系方式",
            "内保负责人", "身份证号码", "联系方式", "地址", "备注"},new int[]{50,50,50,50,50, 50,50,50,200,200}),

    PLACE("place", "实有场所", new String[]{"类型", "名称", "地址", "面积", "出租人",  "身份证号码", "联系方式",
            "租住人", "身份证号码", "联系方式", "备注"},new int[]{50,50,200,50,50, 50,50,50,50,50,200}),

    CAMERA("camera", "实有监控", new String[]{"设备编号", "所属派出所", "分类", "监控地点", "设备类型", "设备朝向", "数量", "备注"},
            new int[]{50,50,50,200,50,50,5,200});

    private String type;
    private String name;
    private String[] value;
    private int[] lengths;

    BeanTypeEnum(String type, String name, String[] value, int[] lengths) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.lengths = lengths;
    }

    public static BeanTypeEnum getEnum(String type) {
        for (BeanTypeEnum item : BeanTypeEnum.values()) {
            if (item.getType().equals(type)) {
                return item;
            }
        }
        return null;
    }

    public int[] getLengths() {
        return lengths;
    }

    public String[] getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
