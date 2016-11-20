package com.baseinfo.collect.controller;


import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.enums.BeanTypeEnum;
import com.baseinfo.collect.util.ExcelFileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Walker on 2016/11/17.
 */
public class DataCommitController {
    private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";

    @RequestMapping(value = "/excelUpload")
    public BaseResponse excelCommit(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam("beanType") int beanType,
                                    @RequestParam("fileContent") MultipartFile fileContent) {
        BaseResponse res = new BaseResponse();
        try {
            if (fileContent.getSize() / 1024 > 100) {//100KB
                res.setCode(-1);
                res.setMsg("文件过大，清手动拆分");
                return res;
            }
            InputStream file = fileContent.getInputStream();

            //读取excel文件方法
            Workbook workbook = createWorkbook(file);
            dealExcel(workbook, BeanTypeEnum.getEnum(beanType), res);
        } catch (Exception e) {
            res.setCode(0);
            res.setMsg("系统错误，请稍后重试");
        }
        return res;
    }


    private Workbook getWorkbookBySuffix(String filePath) throws IOException {
        Workbook workbook = null;
        InputStream is = new FileInputStream(filePath);
        if (filePath.endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (filePath.endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    private Workbook getWorkbook(String excelFile) throws Exception {
        Workbook book = null;
        try {
            book = new XSSFWorkbook(new FileInputStream(excelFile));
        } catch (Exception ex) {
            book = new HSSFWorkbook(new FileInputStream(excelFile));
        }
        return book;
    }

    private Workbook createWorkbook(InputStream inputStream) throws IOException, InvalidFormatException {
        return WorkbookFactory.create(inputStream);
    }

    public static void main(String[] args) throws Exception {
        Workbook wb = new DataCommitController().getWorkbook("C:\\Users\\Walker\\Desktop\\testfile\\实有房屋.xlsx");

        Workbook wb2 = new DataCommitController().createWorkbook(new FileInputStream("C:\\Users\\Walker\\Desktop\\testfile\\camera.xls"));
        new DataCommitController().dealExcel(wb2, BeanTypeEnum.CAMERA, null);
    }


    private void dealExcel(Workbook workbook, BeanTypeEnum beanTypeEnum, BaseResponse response) {
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                response.setCode(-1);
                response.setMsg("excel无工作薄");
                continue;
            }

            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();

            // 读取首行 即,表头
            Row firstRow = sheet.getRow(firstRowIndex);
            //校验数据 excel 形式
            int headColumn = ExcelFileUtil.checkHead(firstRow, beanTypeEnum);
            if (headColumn > -1) {
                response.setCode(-2);
                response.setMsg(String.format("请填写正确的列名:第%d列,%s 为空或错误", headColumn, beanTypeEnum.getValue()[headColumn - 1]));
                return;
            }
            //TODO：test start
            for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
                Cell cell = firstRow.getCell(i);
                String cellValue = ExcelFileUtil.getCellValue(cell, true);
                System.out.print(" " + cellValue + "\t");
            }
            System.out.println("");
            //校验数据
            for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                int colCheck = ExcelFileUtil.checkRowData(sheet.getRow(rowIndex), BeanTypeEnum.CAMERA);
                if (colCheck > -1) {
                    response.setCode(-2);
                    response.setMsg(String.format("文件第%d行,第%d列,%s 数据类型错误", rowIndex, colCheck, BeanTypeEnum.CAMERA.getValue()[colCheck - 1]));
                    //TODO:
                    System.out.println(String.format("文件第%d行,第%d列,%s 错误", rowIndex, colCheck, BeanTypeEnum.CAMERA.getValue()[colCheck - 1]));
                    return;
                }
                Row currentRow = sheet.getRow(rowIndex);// 当前行
                int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
                    Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                    String currentCellValue = ExcelFileUtil.getCellValue(currentCell, true);// 当前单元格的值
                    System.out.print(currentCellValue + "\t");
                }
                System.out.println("");
            }//TODO test end
            // 入库并添加索引
            for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                ExcelFileUtil.insertRowData(sheet.getRow(rowIndex), BeanTypeEnum.CAMERA);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
