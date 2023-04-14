package com.limuyi.controller;

import com.limuyi.domain.Test;
import com.limuyi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController   //组合注解  等于@Controller+@ResponseBody   [@ResponseBody的作用其实是将java对象转为json格式的数据。]
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/findAllTest")
    public List<Test> findAllTest(){
        List<Test> allTest = testService.findAllTest();
        return allTest;
    }

}
