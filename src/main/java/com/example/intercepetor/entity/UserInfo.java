package com.example.intercepetor.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: XueWeiDong
 * @Description: 用于登陆
 * @Date: 10:58 2019/12/5
 */
@Getter
@Setter
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -1465620832943610607L;
    private String userName;

}
