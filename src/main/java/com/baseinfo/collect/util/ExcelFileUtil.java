package com.baseinfo.collect.util;

import com.baseinfo.collect.beans.*;
import com.baseinfo.collect.client.HouseClient;
import com.baseinfo.collect.client.PersonClient;
import com.baseinfo.collect.enums.BeanTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Walker on 2016/11/18.
 */
@Service("ExcelFileUtil")
public class ExcelFileUtil {

    //    public static final String[] people_info = new String[]{"", ""};
//    public static final String[] house_info = new String[]{"", ""};
//    public static final String[] employer_info = new String[]{"", ""};
//    public static final String[] place_info = new String[]{"", ""};
//    public static final String[] camera_info = new String[]{"设备编号", "所属派出所", "分类", "监控地点", "设备类型", "设备朝向", "数量", "备注"};
    @Autowired
    @Qualifier("HouseClient")
    HouseClient houseClient;

    @Autowired
    @Qualifier("PersonClient")
    PersonClient personclient;

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

    //检查数据基本判断
    public int checkRowData(Row row, BeanTypeEnum infoHeadEnum) {
        switch (infoHeadEnum) {
            case PEOPLE:
                try {
                    Integer.parseInt(getCellValue(row.getCell(5), false));
                } catch (Exception e) {
                    return 6;//第七列数量
                }
                break;
            case HOUSE:
                try {
                    Integer.parseInt(getCellValue(row.getCell(6), false));
                } catch (Exception e) {
                    return 7;//第七列数量
                }
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
    public int insertRowData(Row row, BeanTypeEnum infoHeadEnum) {
        int n = -1;
        switch (infoHeadEnum) {
            case PEOPLE:
                try {
                    PeopleBean peopleBean = new PeopleBean();
                    peopleBean.setType(getCellValue(row.getCell(n++), true));
                    peopleBean.setSubtype(getCellValue(row.getCell(n++), true));
                    peopleBean.setHostname(getCellValue(row.getCell(n++), true));
                    peopleBean.setHostid(getCellValue(row.getCell(n++), true));
                    peopleBean.setHostphone(getCellValue(row.getCell(n++), true));
                    peopleBean.setNumber(Integer.parseInt(getCellValue(row.getCell(n++), true)));
                    peopleBean.setLessee(getCellValue(row.getCell(n++), true));
                    peopleBean.setStay(getCellValue(row.getCell(n++), true));
                    peopleBean.setEmployee(getCellValue(row.getCell(n++), true));
                    peopleBean.setExpend(getCellValue(row.getCell(n++), true));

                    peopleBean.setAddtime(new Date());
                    peopleBean.setUpdatetime(new Date());
                    //插入数据库

                    //建立ES索引
                    n = personclient.insertAndIndex(peopleBean) ? 0 : -1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return n + 1;//第七列数量
                }
                break;
            case HOUSE:
                try {
                    HouseBean houseBean = new HouseBean();
                    houseBean.setType(getCellValue(row.getCell(n++), true));
                    houseBean.setSubtype(getCellValue(row.getCell(n++), true));
                    houseBean.setHost(getCellValue(row.getCell(n++), true));
                    houseBean.setOwner(getCellValue(row.getCell(n++), true));
                    houseBean.setOwnerid(getCellValue(row.getCell(n++), true));
                    houseBean.setOwnerphone(getCellValue(row.getCell(n++), true));
                    houseBean.setEmployee(getCellValue(row.getCell(n++), true));
                    houseBean.setLocation(getCellValue(row.getCell(n++), true));
                    houseBean.setUnit(getCellValue(row.getCell(n++), true));
                    houseBean.setFloor(Integer.parseInt(getCellValue(row.getCell(n++), true)));
                    houseBean.setDoornumber(getCellValue(row.getCell(n++), true));
                    houseBean.setExpend(getCellValue(row.getCell(n++), true));

                    //插入数据库

                    //建立ES索引
                    houseClient.insertAndIndex(houseBean);
                } catch (Exception e) {
                    return n + 1;//第七列数量
                }
                break;
            case EMPLOYER:
                try {
                    EmployerBean employerBean = new EmployerBean();
                    employerBean.setType(getCellValue(row.getCell(n++), true));
                    employerBean.setName(getCellValue(row.getCell(n++), true));
                    employerBean.setChargeman(getCellValue(row.getCell(n++), true));
                    employerBean.setChargemanid(getCellValue(row.getCell(n++), true));
                    employerBean.setChargemanphone(getCellValue(row.getCell(n++), true));
                    employerBean.setSafeman(getCellValue(row.getCell(n++), true));
                    employerBean.setSafemanid(getCellValue(row.getCell(n++), true));
                    employerBean.setSafemanphone(getCellValue(row.getCell(n++), true));
                    employerBean.setAddress(getCellValue(row.getCell(n++), true));
                    employerBean.setExtend(getCellValue(row.getCell(n++), true));
                    //插入数据库

                    //建立ES索引

                } catch (Exception e) {
                    return n + 1;//第七列数量
                }
                break;
            case PLACE:
                try {
                    PlaceBean placeBean = new PlaceBean();
                    placeBean.setType(getCellValue(row.getCell(n++), true));
                    placeBean.setName(getCellValue(row.getCell(n++), true));
                    placeBean.setAddress(getCellValue(row.getCell(n++), true));
                    placeBean.setArea(getCellValue(row.getCell(n++), true));
                    placeBean.setLessor(getCellValue(row.getCell(n++), true));
                    placeBean.setLessorid(getCellValue(row.getCell(n++), true));
                    placeBean.setLessorphone(getCellValue(row.getCell(n++), true));
                    placeBean.setLessee(getCellValue(row.getCell(n++), true));
                    placeBean.setLesseeid(getCellValue(row.getCell(n++), true));
                    placeBean.setLesseephone(getCellValue(row.getCell(n++), true));
                    placeBean.setExtend(getCellValue(row.getCell(n++), true));
                    //插入数据库

                    //建立ES索引

                } catch (Exception e) {
                    return n + 1;//第七列数量
                }
                break;
            case CAMERA:
                try {
                    CameraBean cameraBean = new CameraBean();
                    cameraBean.setDeviceid(getCellValue(row.getCell(n++), true));
                    cameraBean.setPolicestation(getCellValue(row.getCell(n++), true));
                    cameraBean.setType(getCellValue(row.getCell(n++), true));
                    cameraBean.setLocalname(getCellValue(row.getCell(n++), true));
                    cameraBean.setDevicetype(getCellValue(row.getCell(n++), true));
                    cameraBean.setDirection(getCellValue(row.getCell(n++), true));
                    cameraBean.setCount(Integer.parseInt(getCellValue(row.getCell(n++), true)));
                    cameraBean.setExpend(getCellValue(row.getCell(n++), true));
                    //插入数据库

                    //建立ES索引

                } catch (Exception e) {
                    return n + 1;//第七列数量
                }
                break;
            default:
                break;
        }
        return n;//数据基本正常
    }

    public Workbook getExcel(BeanTypeEnum beanTypeEnum) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(beanTypeEnum.getName());
        HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFRow row = (HSSFRow) sheet.createRow(0);
        HSSFCell cell;
        for (int column = 0; column < beanTypeEnum.getValue().length; column++) {
            cell = row.createCell(column);
            cell.setCellValue(beanTypeEnum.getValue()[column]);
            cell.setCellStyle(style);
        }
        return workbook;
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
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

}
