package com.limuyi.service;

import com.github.pagehelper.PageInfo;
import com.limuyi.domain.PromotionAd;
import com.limuyi.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {
    /*
        分页查询广告信息
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);


    /*
        新增广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
        回显广告信息
     */

    public PromotionAd findPromotionAdById(int id);

    /*
        修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
        广告状态上下线
     */
    public void updatePromotionAdStatus(int id,int status);
}
