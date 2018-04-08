package com.wxd.toutiao.dao;

import com.wxd.toutiao.domain.ImageNews;
import com.wxd.toutiao.domain.ImagesDetail;
import com.wxd.toutiao.exception.ToutiaoException;

import java.util.List;

public interface ImageNewsDao {
    /**
     * 查询图片文档中的图片列表
     * @param category
     * @param maxBehotTime
     * @return
     */
    public List<ImageNews> findImageNews(String category, long maxBehotTime) throws Exception;


    /**
     * 根据关键字查找图片新闻详情
     * @param uuid
     * @return
     */
    public ImagesDetail findImageNewsDetails(String uuid) throws ToutiaoException;
}
