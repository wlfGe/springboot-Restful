package com.atguigu.springboot.exception;

/**
 * @author 王龙飞
 * @version 1.0
 * @title: UserNotExistException
 * @projectName spring-boot-web-crud
 * @description:
 * @date 2020/10/25   9:37
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在");
    }
}
