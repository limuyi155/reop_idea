package com.limuyi.service;

import com.github.pagehelper.PageInfo;
import com.limuyi.domain.Resource;
import com.limuyi.domain.ResourceVO;

import java.util.List;

public interface ResourceService {
    /*
        资源分页&多条件查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);
}
