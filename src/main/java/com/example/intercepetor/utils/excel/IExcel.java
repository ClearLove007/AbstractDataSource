package com.example.intercepetor.utils.excel;

import org.apache.poi.ss.usermodel.Row;

/**
 * @Author: XueWeiDong
 * @Description: 要生成excel文件的对象实现该接口
 * @Date: 14:04 2019/12/23
 */
public interface IExcel {
     void write(Row row);
}
