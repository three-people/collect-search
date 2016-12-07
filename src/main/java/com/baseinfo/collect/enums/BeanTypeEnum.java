package com.baseinfo.collect.enums;

/**
 * Created by Walker on 2016/11/20.
 */
public enum BeanTypeEnum {

    PEOPLE("people", "实有人口",
            new String[]{"楼宇功能", "类型", "户主/出租人", "身份证号码", "联系方式", "人数",
                    "租住人", "寄住人", "从业人员", "备注", "警务区", "居委会"},
            new int[]{/*楼宇功能*/50,/*类型*/ 50,/*户主/出租人*/ 50,/*身份证号码*/ 50, /*联系方式*/50,/*人数*/ 6,
                    /*租住人*/50, /*寄住人*/50,/*从业人员*/ 2000,/*备注*/ 200,/*警务区*/ 50,/*居委会*/ 50}),

    HOUSE("house", "实有房屋",
            new String[]{"楼宇功能", "类别", "产权人", "户主/房主", "身份证号码", "联系方式", "从业人员/家庭成员",
                    "地址", "单元", "楼层", "门牌号", "备注", "警务区", "居委会"},
            new int[]{/*楼宇功能*/50,/*类别*/ 50,/*产权人*/ 50, /*户主/房主*/50,/*身份证号码*/ 50,/*联系方式*/ 50, /*从业人员/家庭成员*/2000,
                    /*地址*/200, /*单元*/50, /*楼层*/3,/*门牌号*/ 6, /*备注*/200,/*警务区*/ 50,/*居委会*/ 50}),

    EMPLOYER("employer", "实有单位", new String[]{"单位级别", "名称", "负责人", "身份证号码", "联系方式",
            "内保负责人", "身份证号码", "联系方式", "地址", "备注", "警务区", "居委会"},
            new int[]{/*单位级别*/50, /*名称*/50, /*负责人*/50, /*身份证号码*/50, /*联系方式*/50,
                    /*内保负责人*/50, /*身份证号码*/50, /*联系方式*/50, /*地址*/200, /*备注*/200, /*警务区*/50,/*居委会*/ 50}),

    PLACE("place", "实有场所",
            new String[]{"类型", "名称", "地址", "面积", "出租人", "身份证号码", "联系方式",
                    "租住人", "身份证号码", "联系方式", "备注", "警务区", "居委会"},
            new int[]{/*类型*/50, /*名称*/50, /*地址*/200, /*面积*/50, /*出租人*/50,/*身份证号码*/ 50, /*联系方式*/50,
                    /*租住人*/50, /*身份证号码*/50, /*联系方式*/50, /*备注*/200, /*警务区*/50,/*居委会*/ 50}),

    CAMERA("camera", "实有监控",
            new String[]{"设备编号", "所属派出所", "分类", "监控地点", "设备类型", "设备朝向",
                    "数量", "备注", "警务区", "居委会"},
            new int[]{/*设备编号*/50,/*所属派出所*/ 50, /*分类*/50, /*监控地点*/200, /*设备类型*/50, /*设备朝向*/50,
                    /*数量*/5, /*数量*/200, /*警务区*/50,/*居委会*/ 50});

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
