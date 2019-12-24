package com.example.intercepetor.service;

import com.example.intercepetor.common.exception.ServiceException;
import com.example.intercepetor.utils.date.DateUtils;
import com.example.intercepetor.utils.excel.ExcelWriter;
import com.example.intercepetor.utils.excel.IExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 15:24 2019/12/23
 */
@Service
@Slf4j
public class ExcelService {

    public String writeExcelTest(List<IExcel> records, String[] headers){
        ExcelWriter excelWriter = new ExcelWriter(new HSSFWorkbook());
        excelWriter.write(records, headers);
        try {
            StringBuilder exportPath = new StringBuilder("excel-export/");
            exportPath.append("excelTest");
            String dateString = DateUtils.getCurrentDateString("yyyymmddHHmmss");
            exportPath.append(dateString).append(".xls");
            excelWriter.out(exportPath.toString());
            return exportPath.toString();
        }catch (IOException e){
            throw new ServiceException(e);
        }
    }
}
