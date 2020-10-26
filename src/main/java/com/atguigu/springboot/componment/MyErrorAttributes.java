package com.atguigu.springboot.componment;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author 王龙飞
 * @version 1.0
 * @title: MyErrorAttributes
 * @projectName spring-boot-web-crud
 * @description:
 * @date 2020/10/25   10:26
 */
// 给容器中加入我们自己定义的ErrorAttributes
@Component
public class MyErrorAttributes  extends DefaultErrorAttributes {

    // 返回的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String,Object> map =super.getErrorAttributes(webRequest, options);
        map.put("company","atguigu");

        // 从请求域 异常处理器携带的数据
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext",0);
        map.put("ext",ext);
        return map;
    }
}
