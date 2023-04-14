package com.limuyi.dao;

import com.limuyi.domain.Course;
import com.limuyi.domain.CourseVO;
import com.limuyi.domain.PromotionAd;
import com.limuyi.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdMapper {
    /*
        分页查询广告信息
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /*
          新增广告信息
     */

    public void savePromotionAd(PromotionAd promotionAd);

    /*
        回显广告信息
     */

    public PromotionAd findPromotionAdById(int id);

    /*
        更新广告信息
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
        广告状态上下线
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
