package com.baseinfo.collect.util;

import com.baseinfo.collect.beans.*;
import com.baseinfo.collect.client.*;
import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.enums.BeanTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Walker on 2016/11/18.
 */
@Service("ExcelFileUtil")
public class ExcelFileUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExcelFileUtil.class);

    @Autowired
    @Qualifier("PlaceClient")
    PlaceClient placeClient;

    @Autowired
    @Qualifier("PersonClient")
    PersonClient personclient;

    @Autowired
    @Qualifier("CameraClient")
    CameraClient cameraCient;

    @Autowired
    @Qualifier("EmployerClient")
    EmployerClient employerClient;

    @Autowired
    @Qualifier("HouseClient")
    HouseClient houseClient;


    //检查excel 表头
    public int checkHead(Row headRow, BeanTypeEnum infoEnum) {
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

    //检查数据 长度判断
    public String checkCellLength(Row row, BeanTypeEnum typeEnum) {
        if (row != null) {
            for (int i = 0; i < typeEnum.getValue().length; i++) {
                if (typeEnum.getLengths()[i] > 49) {
                    if (getCellValue(row.getCell(i), true).trim().length() > typeEnum.getLengths()[i]) {
                        return String.format("第%d列 \"%s\" 已超过%d字", i + 1, typeEnum.getValue()[i], typeEnum.getLengths()[i]);
                    }
                }
            }
        }
        return "";
    }

    //检查数据基本判断
    public int checkRowData(Row row, BeanTypeEnum infoHeadEnum) {
        boolean isNullRow = true;
        for (int i = 0; i < infoHeadEnum.toString().length(); i++) {//判断是否是空行
            if (row != null && getCellValue(row.getCell(i), true).trim().length() > 1) isNullRow = false;
        }
        if (isNullRow) return -2;
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
                    Integer.parseInt(getCellValue(row.getCell(9), false));
                } catch (Exception e) {
                    return 10;//第10列数量
                }
                break;
            case EMPLOYER:
                break;
            case PLACE:
                break;
            case CAMERA:
                try {
                    Integer.parseInt(getCellValue(row.getCell(6), false));
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
    public int insertRowData(Row row, BeanTypeEnum infoHeadEnum, BaseResponse baseResponse) {
        int n = -1;
        List<String> cellList = new ArrayList<>();
        //登录信息
        String loginId = (String) baseResponse.getData().get("loginid");
        String realName = (String) baseResponse.getData().get("realName");
        switch (infoHeadEnum) {
            case PEOPLE:
                try {
                    n = 0;
                    PeopleBean peopleBean = new PeopleBean();
                    peopleBean.setType(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setSubtype(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setHostname(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setHostid(getAndAddCellValue(row.getCell(n++), cellList, false));
                    peopleBean.setHostphone(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setNumber(Integer.parseInt(getAndAddCellValue(row.getCell(n++), cellList, true)));
                    peopleBean.setLessee(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setStay(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setEmployee(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setExpend(getAndAddCellValue(row.getCell(n++), cellList, true));

                    peopleBean.setAddtime(new Date());
                    peopleBean.setUpdatetime(new Date());

                    peopleBean.setPolicearea(getAndAddCellValue(row.getCell(n++), cellList, true));
                    peopleBean.setNeighbor(getAndAddCellValue(row.getCell(n++), cellList, true));

                    peopleBean.setPoliceid(loginId);
                    peopleBean.setPolicename(realName);
                    //插入数据库

                    //建立ES索引
                    n = personclient.insertAndIndex(peopleBean) ? 0 : -1;
                    if (n == 0) {
                        List<Long> resList = (ArrayList<Long>) baseResponse.getData().get("idList");
                        resList.add(peopleBean.getId());
                    }
                } catch (Exception e) {
                    logger.error(infoHeadEnum.getName() + " " + infoHeadEnum.getType() + " error:" + e.getMessage());
                    return n + 1;//第七列数量
                }
                break;
            case HOUSE:
                try {
                    n = 0;
                    HouseBean houseBean = new HouseBean();
                    houseBean.setType(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setSubtype(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setHost(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setOwner(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setOwnerid(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setOwnerphone(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setEmployee(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setLocation(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setUnit(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setFloor(Integer.parseInt(getAndAddCellValue(row.getCell(n++), cellList, true)));
                    houseBean.setDoornumber(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setExpend(getAndAddCellValue(row.getCell(n++), cellList, true));

                    houseBean.setAddtime(new Date());
                    houseBean.setUpdatetime(new Date());

                    houseBean.setPolicearea(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setNeighbor(getAndAddCellValue(row.getCell(n++), cellList, true));
                    houseBean.setPoliceid(loginId);
                    houseBean.setPolicename(realName);
                    //插入数据库

                    //建立ES索引
                    n = houseClient.insertAndIndex(houseBean) ? 0 : -1;
                    if (n == 0) {
                        List<Long> resList = (ArrayList<Long>) baseResponse.getData().get("idList");
                        resList.add(houseBean.getId());
                    }
                } catch (Exception e) {
                    logger.error(infoHeadEnum.getName() + " " + infoHeadEnum.getType() + " error:" + e.getMessage());
                    return n + 1;//第七列数量
                }
                break;
            case EMPLOYER:
                try {
                    n = 0;
                    EmployerBean employerBean = new EmployerBean();
                    employerBean.setType(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setName(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setChargeman(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setChargemanid(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setChargemanphone(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setSafeman(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setSafemanid(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setSafemanphone(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setAddress(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setExtend(getAndAddCellValue(row.getCell(n++), cellList, true));

                    employerBean.setAddtime(new Date());
                    employerBean.setUpdatetime(new Date());

                    employerBean.setPolicearea(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setNeighbor(getAndAddCellValue(row.getCell(n++), cellList, true));
                    employerBean.setPoliceid(loginId);
                    employerBean.setPolicename(realName);

                    //插入数据库

                    //建立ES索引
                    n = employerClient.insertAndIndex(employerBean) ? 0 : -1;
                    if (n == 0) {
                        List<Long> resList = (ArrayList<Long>) baseResponse.getData().get("idList");
                        resList.add(employerBean.getId());
                    }
                } catch (Exception e) {
                    logger.error(infoHeadEnum.getName() + " " + infoHeadEnum.getType() + " error:" + e.getMessage());
                    return n + 1;//第七列数量
                }
                break;
            case PLACE:
                try {
                    n = 0;
                    PlaceBean placeBean = new PlaceBean();
                    placeBean.setType(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setName(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setAddress(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setArea(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setLessor(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setLessorid(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setLessorphone(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setLessee(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setLesseeid(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setLesseephone(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setExtend(getAndAddCellValue(row.getCell(n++), cellList, true));

                    placeBean.setAddtime(new Date());
                    placeBean.setUpdatetime(new Date());

                    placeBean.setPolicearea(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setNeighbor(getAndAddCellValue(row.getCell(n++), cellList, true));
                    placeBean.setPoliceid(loginId);
                    placeBean.setPolicename(realName);
                    //插入数据库

                    //建立ES索引
                    n = placeClient.insertAndIndex(placeBean) ? 0 : -1;
                    if (n == 0) {
                        List<Long> resList = (ArrayList<Long>) baseResponse.getData().get("idList");
                        resList.add(placeBean.getId());
                    }
                } catch (Exception e) {
                    logger.error(infoHeadEnum.getName() + " " + infoHeadEnum.getType() + " error:" + e.getMessage());
                    return n + 1;//第七列数量
                }
                break;
            case CAMERA:
                try {
                    n = 0;
                    CameraBean cameraBean = new CameraBean();
                    cameraBean.setDeviceid(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setPolicestation(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setType(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setLocalname(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setDevicetype(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setDirection(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setCount(Integer.parseInt(getAndAddCellValue(row.getCell(n++), cellList, true)));
                    cameraBean.setExpend(getAndAddCellValue(row.getCell(n++), cellList, true));

                    cameraBean.setAddtime(new Date());
                    cameraBean.setUpdatetime(new Date());

                    cameraBean.setPolicearea(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setNeighbor(getAndAddCellValue(row.getCell(n++), cellList, true));
                    cameraBean.setPoliceid(loginId);
                    cameraBean.setPolicename(realName);
                    //插入数据库

                    //建立ES索引
                    n = cameraCient.insertAndIndex(cameraBean) ? 0 : -1;
                    if (n == 0) {
                        List<Long> resList = (ArrayList<Long>) baseResponse.getData().get("idList");
                        resList.add(cameraBean.getId());
                    }
                } catch (Exception e) {
                    logger.error(infoHeadEnum.getName() + " " + infoHeadEnum.getType() + " error:" + e.getMessage());
                    return n + 1;//第七列数量
                }
                break;
            default:
                break;
        }
        if (n == 0) {
            List<Object> resList = (ArrayList) baseResponse.getData().get("resList");
            resList.add(cellList);
//                        baseResponse.getResList().add(peopleBean);
        }
        return n;//数据基本正常
    }

    public Workbook getExcel(BeanTypeEnum beanTypeEnum) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(beanTypeEnum.getName());
        HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFRow row = (HSSFRow) sheet.createRow(0);
        HSSFCell cell;
        for (int column = 0; column < beanTypeEnum.getValue().length; column++) {
            cell = row.createCell(column);
            cell.setCellValue(beanTypeEnum.getValue()[column]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(0);
        }
        return workbook;
    }

    public String getCellValue(Cell cell, boolean treatAsStr) {
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
            return String.valueOf((long) cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue()).trim();
        }
    }

    private String getAndAddCellValue(Cell cell, List<String> rowList, boolean treatAsStr) {
        if (cell == null) {
            rowList.add("");
            return "";
        }

        if (treatAsStr) {
            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
            // 加上下面这句，临时把它当做文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        String str = "";
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            str = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            str = String.valueOf((long) cell.getNumericCellValue());
        } else {
            str = String.valueOf(cell.getStringCellValue());
        }
        rowList.add(str);
        return str.trim();
    }


}
