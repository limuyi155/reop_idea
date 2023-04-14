package com.limuyi.service;

import com.limuyi.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    /*
       获取所有广告位
    */
    public List<PromotionSpace> findAllPromotionSpace();

    /*
        添加广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*
        修改广告位
     */
    void updatePromotionSpace(PromotionSpace promotionSpace);

    /*
        根据Id查询广告位信息
     */
    public PromotionSpace findPromotionSpaceById(int id);
}
