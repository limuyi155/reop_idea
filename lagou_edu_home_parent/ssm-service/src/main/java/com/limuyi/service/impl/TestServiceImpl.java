package com.limuyi.service.impl;

import com.limuyi.dao.TestMapper;
import com.limuyi.domain.Test;
import com.limuyi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //生成该类的实力存到IOC容器中
public class TestServiceImpl implements TestService {
    //注入代理对象
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAllTest() {
        List<Test> allTest = testMapper.findAllTest();
        return allTest;
    }
}
