package com.example.intercepetor.controller;

import com.example.intercepetor.datasource.datasourceConfig.secondConfig.DataSourceDTO;
import com.example.intercepetor.dto.RequestDTO;
import com.example.intercepetor.dto.ResponseDTO;
import com.example.intercepetor.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 9:31 2019/11/29
 */
@RestController
@RequestMapping("/result")
@Slf4j
public class ReciveController {

    @Autowired
    private DataSourceDTO dataSourceDTO;

    @RequestMapping(value = "/v1/interception", method = RequestMethod.POST)
    public ResponseDTO token(@RequestBody RequestDTO requestDTO){
        String result = JsonUtils.objectToJson(requestDTO);
        log.info("请求参数为：{}",result );
        return new ResponseDTO(requestDTO);
    }

    @RequestMapping(value = "/testErr", method = RequestMethod.GET)
    public ResponseDTO testErr(){
        System.out.println(dataSourceDTO.getDatasource().get("master").get("username"));
        return new ResponseDTO();
    }
}
