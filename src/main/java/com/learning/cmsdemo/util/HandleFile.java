package com.learning.cmsdemo.util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HandleFile {
    public static HSSFWorkbook createExcel(Map<String, Object> excelMap) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = excelMap.get("sheetName").toString();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(3, 17 * 256);
        sheet.setColumnWidth(4, 17 * 256);
        sheet.setColumnWidth(5, 17 * 256);
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBoldweight((short) 14);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);
        boolean isSerial = (boolean) excelMap.get("isSerial");
        List<String> headerList = (List<String>) excelMap.get("header");
        HSSFCell cell;

        int headRow=0;
        if (isSerial){
            cell = row.createCell(0);
            cell.setCellValue("序号");
            cell.setCellStyle(style);
            headRow = 1;

        }
        for (String header : headerList) {
            cell = row.createCell(headRow);
            cell.setCellValue(header);
            cell.setCellStyle(style);
            headRow++;
        }
        List<List<String>> data = (List<List<String>>) excelMap.get("data");

        int rowNum = 1;

        if (isSerial){
            int headSize = headerList.size() + 1;
            for (List<String> obj:data){
                HSSFRow currRow = sheet.createRow(rowNum);
                for (int i=1;i<headSize;i++){
                    currRow.createCell(0).setCellValue(rowNum);
                    currRow.createCell(i).setCellValue(obj.get(i-1));
                }
                rowNum++;
            }
        }else{
            int headSize = headerList.size();
            for (List<String> obj:data){
                HSSFRow currRow = sheet.createRow(rowNum);
                for (int i=0;i<headSize;i++){
                    currRow.createCell(i).setCellValue(obj.get(i));
                }
                rowNum++;
            }
        }

        return workbook;
    }


    public static void buildExcelFile(String filename, HSSFWorkbook workbook) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }


    public static void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
    }


}
