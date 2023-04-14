package com.limuyi.controller;

import com.github.pagehelper.PageInfo;
import com.limuyi.domain.Resource;
import com.limuyi.domain.ResourceVO;
import com.limuyi.domain.ResponseResult;
import com.limuyi.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    /*
        资源分页&多条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVO resourceVO){
        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVO);
        return new ResponseResult(true,200,"资源分页&多条件查询成功",pageInfo);

    }

}
