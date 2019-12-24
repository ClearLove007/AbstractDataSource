package com.example.intercepetor.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.ObjectUtils;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 11:29 2019/12/23
 */
public abstract class ExcelBase {

    protected Workbook workbook;
    protected Sheet sheet;

    public ExcelBase(Workbook workbook){
        this(workbook, 0);
    }

    public ExcelBase(Workbook workbook, int sheetNo){
        this.workbook = workbook;
        this.sheet = sheet(sheetNo);
    }

    protected Sheet sheet(int sheetNo){
        Sheet sheet = null;
        try {
            sheet = workbook.getSheetAt(sheetNo);
            if (ObjectUtils.isEmpty(sheet)){
                sheet = workbook.createSheet("sheet" + (sheetNo + 1));
            }
        }catch (IllegalArgumentException e){
            sheet = workbook.createSheet("sheet" + (sheetNo + 1));
        }
        sheet.setDefaultRowHeightInPoints(15);
        sheet.setDefaultColumnWidth(25);
        return sheet;
    }

    protected Row row(int rowNo){
        Row row = sheet.getRow(rowNo);
        if (ObjectUtils.isEmpty(row)){
            row = sheet.createRow(rowNo);
        }
        return row;
    }

    protected Cell cell(Row row, int cellNo){
        Cell cell = row.getCell(cellNo);
        if (ObjectUtils.isEmpty(cell)){
            cell = row.createCell(cellNo);
        }
        return cell;
    }

    protected Cell cell(int rowNo, int cellNo){
        Row row = row(rowNo);
        return cell(row, cellNo);
    }

}
