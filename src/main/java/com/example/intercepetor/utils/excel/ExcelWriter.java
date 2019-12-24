package com.example.intercepetor.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.List;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 13:56 2019/12/23
 */
public class ExcelWriter extends ExcelBase {

    public ExcelWriter(Workbook workbook) {
        super(workbook);
    }

    public ExcelWriter(Workbook workbook, int sheetNo) {
        super(workbook, sheetNo);
    }

    public void write(List<IExcel> records, Object headerData){
        writeHeader(headerData, 0);
        writeBody(records, 1);
    }

    public void out(String path) throws IOException {
        File file = new File(path.substring(0, path.lastIndexOf("/")));
        if (!file.exists()){
            file.mkdir();
        }
        OutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
        outputStream.close();
    }

    private void writeHeader(Object headerData, int headerNo){
        String[] heads = (String[])headerData;
        Row row = row(headerNo);
        for (int i = 0; i < heads.length; i++){
            Cell cell = cell(row, i);
            cell.setCellValue(heads[i]);
        }
    }

    private void writeBody(List<IExcel> records, int startRowNo){
        int rowNo = startRowNo;
        for (IExcel excel : records){
            excel.write(row(rowNo));
            rowNo++;
        }
    }
}
