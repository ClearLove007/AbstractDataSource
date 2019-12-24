package com.example.intercepetor.entity;

import com.example.intercepetor.utils.excel.ExcelWriteUtil;
import com.example.intercepetor.utils.excel.IExcel;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 15:17 2019/12/23
 */
@Data
public class ExcelTest implements IExcel {
    private String schoolName;
    private Double score;
    @Override
    public void write(Row row) {
        ExcelWriteUtil.writeString(row.createCell(0), schoolName);
        ExcelWriteUtil.writeDouble(row.createCell(1), score);
    }
}
