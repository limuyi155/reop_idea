package com.limuyi.service;

import com.limuyi.domain.Test;

import java.util.List;

public interface TestService {
    /*
        对test表进行查询所有
     */
    public List<Test> findAllTest();
}
