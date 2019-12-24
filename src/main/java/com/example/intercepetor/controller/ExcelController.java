package com.example.intercepetor.controller;

import com.example.intercepetor.dto.ResponseDTO;
import com.example.intercepetor.service.ExcelService;
import com.example.intercepetor.utils.excel.IExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 15:19 2019/12/23
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/export")
    public ResponseDTO exportExcelTest(){
        String[] headers = new String[]{"学校名称", "学校分数"};
        List<IExcel> excelTestList = new ArrayList<>();
        String path = excelService.writeExcelTest(excelTestList, headers);
        return new ResponseDTO("path", path);
    }
}
