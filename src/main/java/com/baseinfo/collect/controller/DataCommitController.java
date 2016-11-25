package com.baseinfo.collect.controller;


import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.enums.BeanTypeEnum;
import com.baseinfo.collect.util.ExcelFileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Walker on 2016/11/17.
 */

@Controller
public class DataCommitController {

    private static final Logger logger = LoggerFactory.getLogger(DataCommitController.class);
    private static final String EXTENSION_XLS = ".xls";
    private static final String EXTENSION_XLSX = ".xlsx";

    @Autowired
    @Qualifier("ExcelFileUtil")
    ExcelFileUtil excelFileUtil;


    @RequestMapping(value = "/main")
    public ModelAndView excelPage(HttpServletRequest request,
                                  HttpServletResponse response) {

        String error = request.getParameter("error");
        if (error != null) {
        }
        ModelAndView model = new ModelAndView("/uploaddata/upload");
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/excelUpload")
    public ModelAndView excelCommit(
//                                    @RequestParam("beanType") int beanType,
//            @RequestParam("multifile") MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response) {
        String beanType = request.getParameter("type");
        BeanTypeEnum typeEnum = BeanTypeEnum.getEnum(beanType);
        BaseResponse res = new BaseResponse();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("multifile");
        //String sourceName = file.getOriginalFilename(); // 原始文件名
        if (checkFile(file, res)) {   //文件后缀判断
            try {
                InputStream fileInput = file.getInputStream();
                //读取excel文件方法
                Workbook workbook = createWorkbook(fileInput);
                dealExcel(workbook, typeEnum, res);

            } catch (Exception e) {
                res.setCode(0);
                res.setMsg("系统错误，请稍后重试");
                e.printStackTrace();
                logger.error("上传文件error:", e);
            }
        }
        ModelMap model = new ModelMap();
        if (res != null) {
            model.addAttribute("code", res.getCode());
            model.addAttribute("msg", res.getMsg());
            if (res.getData() != null) {
                model.addAttribute("headList", res.getData().get("headList"));
                model.addAttribute("resList", res.getData().get("resList"));
                model.addAttribute("idList", getIdsByList((ArrayList<Long>) res.getData().get("idList")));
            }
        }
        ModelAndView modelAndView = new ModelAndView("/upload", model);
        return modelAndView;
    }

    /**
     * 获取ids 字符串
     *
     * @param idList
     * @return
     */
    private String getIdsByList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < idList.size(); i++) {
            sb.append(String.valueOf(idList.get(i)));
            if (i < idList.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    @RequestMapping(value = "/excelDownload")
    public void excelDown(HttpServletRequest request,
                          HttpServletResponse response) {
        try {

            String beanType = request.getParameter("type");
            BeanTypeEnum typeEnum = BeanTypeEnum.getEnum(beanType);
            String realname = typeEnum.getName() + ".xls";
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流

            HSSFWorkbook workbook = (HSSFWorkbook) excelFileUtil.getExcel(typeEnum);
            OutputStream out = response.getOutputStream();

            // 创建缓冲区
            workbook.write(out);
            // 关闭输出流
            out.close();

        } catch (Exception e) {
            logger.error("下载模版 error", e);
            e.printStackTrace();
        }
    }

    /**
     * 检查上传文件流 基本属性和限制
     *
     * @param file
     * @param baseResponse
     * @return
     */
    private boolean checkFile(MultipartFile file, BaseResponse baseResponse) {
        try {
            if (file.getSize() == 0) {
                baseResponse.setCode(-1);
                baseResponse.setMsg("未选择上传文件");
                return false;
            }
            if (file.getSize() > 1000 * 1000 * 5) {
                baseResponse.setCode(-1);
                baseResponse.setMsg(String.format("文件所占空间%dMB,请拆分文件小于5MB", file.getSize() / 1024 / 1024));
                return false;
            }
            String sourceName = file.getOriginalFilename();
            if (!sourceName.endsWith(EXTENSION_XLS) && !sourceName.endsWith(EXTENSION_XLSX)) {
                baseResponse.setCode(-1);
                baseResponse.setMsg("文件格式错误");
                return false;
            }
        } catch (Exception e) {
            baseResponse.setCode(-1);
            baseResponse.setMsg("上传的文件有问题");
            return false;
        }
        return true;
    }

    /**
     * 是否是excel文件后缀
     *
     * @param file
     * @return
     */
    private boolean isExcelFileSuffix(String file, BaseResponse baseResponse) {
        baseResponse.setCode(-1);
        baseResponse.setMsg("文件格式错误");
        if (file.endsWith(EXTENSION_XLS) || file.endsWith(EXTENSION_XLSX)) {
            return true;
        }
        return false;
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

    private Workbook createWorkbook(InputStream inputStream) {
        try {
            return WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Workbook wb = new DataCommitController().getWorkbook("C:\\Users\\Walker\\Desktop\\testfile\\实有房屋.xlsx");

        Workbook wb2 = new DataCommitController().createWorkbook(new FileInputStream("C:\\Users\\Walker\\Desktop\\testfile\\camera.xls"));
        new DataCommitController().dealExcel(wb2, BeanTypeEnum.CAMERA, null);
    }


    private void dealExcel(Workbook workbook, BeanTypeEnum beanTypeEnum, BaseResponse response) {
        if (workbook == null) {
            response.setCode(-1);
            response.setMsg("为选择文件");
            return;
        }
        int sheetNum = workbook.getNumberOfSheets();
        if (sheetNum < 1 || workbook.getSheetAt(sheetNum - 1) == null) {
            response.setCode(-1);
            response.setMsg("excel无工作薄");
            return;
        }
        Sheet sheet = workbook.getSheetAt(sheetNum - 1);

        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndex = sheet.getLastRowNum();
        if (firstRowIndex == lastRowIndex) {
            response.setCode(-1);
            response.setMsg("无数据行");
            return;
        }
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
            int colCheck = excelFileUtil.checkRowData(sheet.getRow(rowIndex), beanTypeEnum);
            if (colCheck > -1) {
                response.setCode(-2);
                response.setMsg(String.format("文件第%d行,第%d列,%s 数据类型错误", rowIndex + 1, colCheck, beanTypeEnum.getValue()[colCheck - 1]));
                //TODO:
                System.out.println(String.format("文件第%d行,第%d列,%s 错误", rowIndex + 1, colCheck, beanTypeEnum.getValue()[colCheck - 1]));
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
        int successCount = 0;
        response.setResList(new ArrayList<Object>());
        response.setData(new HashMap<String, Object>());
        response.getData().put("resList", new ArrayList<Object>());
        response.getData().put("headList", beanTypeEnum.getValue());
        response.getData().put("idList", new ArrayList<Long>());
        for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
            int colCheck = excelFileUtil.checkRowData(sheet.getRow(rowIndex), beanTypeEnum);
            if (colCheck == -1) {
                int numres = excelFileUtil.insertRowData(sheet.getRow(rowIndex), beanTypeEnum, response);
                if (numres == 0) successCount++;
            }
            /*try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        if (successCount > 0) {
            response.setMsg("添加数据成功");
            response.setCode(1);
        }
    }

}
