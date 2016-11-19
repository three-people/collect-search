package com.baseinfo.collect.util;

import com.baseinfo.collect.beans.CameraBean;
import com.baseinfo.collect.enums.BeanTypeEnum;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by Walker on 2016/11/18.
 */
public class ExcelFileUtil {
    public static final String[] people_info = new String[]{"", ""};
    public static final String[] house_info = new String[]{"", ""};
    public static final String[] employer_info = new String[]{"", ""};
    public static final String[] place_info = new String[]{"", ""};
    public static final String[] camera_info = new String[]{"设备编号", "所属派出所", "分类", "监控地点", "设备类型", "设备朝向", "数量", "备注"};


    //检查excel 表头
    public static int checkHead(Row headRow, BeanTypeEnum infoEnum) {
        if (headRow == null) return -1;
        for (int i = 0; i < infoEnum.getValue().length; i++) {
            if (headRow.getCell(i) == null) return i;
            String rowStr = getCellValue(headRow.getCell(i), true);
            if (!infoEnum.getValue()[i].equals(rowStr)) {
                return i;
            }
        }
        return -1;
    }

    //检查数据
    public static int checkRowData(Row row, BeanTypeEnum infoHeadEnum) {
        switch (infoHeadEnum) {
            case PEOPLE:
                break;
            case HOUSE:
                break;
            case EMPLOYER:
                break;
            case PLACE:
                break;
            case CAMERA:
                try {
                    Integer.parseInt(getCellValue(row.getCell(6), true));
                } catch (Exception e) {
                    return 7;//第七列数量
                }
                break;
            default:
                break;
        }
        return -1;//数据基本正常
    }

    //处理数据
    public static int insertRowData(Row row, BeanTypeEnum infoHeadEnum) {
        switch (infoHeadEnum) {
            case PEOPLE:
                break;
            case HOUSE:
                break;
            case EMPLOYER:
                break;
            case PLACE:
                break;
            case CAMERA:
                try {
                    CameraBean cameraBean = new CameraBean();
                    cameraBean.setDeviceid(getCellValue(row.getCell(0), true));
                    cameraBean.setPolicestation(getCellValue(row.getCell(1), true));
                    cameraBean.setType(getCellValue(row.getCell(2), true));
                    cameraBean.setLocalname(getCellValue(row.getCell(3), true));
                    cameraBean.setDevicetype(getCellValue(row.getCell(4), true));
                    cameraBean.setDirection(getCellValue(row.getCell(5), true));
                    cameraBean.setCount(Integer.parseInt(getCellValue(row.getCell(6), true)));
                    cameraBean.setExpend(getCellValue(row.getCell(7), true));
                    //插入数据库

                    //建立ES索引

                } catch (Exception e) {
                    return 7;//第七列数量
                }
                break;
            default:
                break;
        }
        return -1;//数据基本正常
    }

    public static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }

        if (treatAsStr) {
            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
            // 加上下面这句，临时把它当做文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

}
