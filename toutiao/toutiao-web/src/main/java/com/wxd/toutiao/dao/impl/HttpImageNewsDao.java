package com.wxd.toutiao.dao.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxd.toutiao.domain.ImagesDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxd.toutiao.dao.ImageNewsDao;
import com.wxd.toutiao.domain.ImageNews;
import com.wxd.toutiao.exception.ToutiaoException;
import com.wxd.toutiao.util.TouTiaoUtils;

@Repository("httpImageNewsDao")
public class HttpImageNewsDao extends AbstractTouTiaoDao implements ImageNewsDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpImageNewsDao.class);
    private final static String SUCCESS = "success";
    ObjectMapper om = new ObjectMapper();

    @Override
    public List<ImageNews> findImageNews(String category, long maxBehotTime) throws ToutiaoException {
        Map<String, String> header = getHeader();
        String signature = getSignature(String.valueOf(maxBehotTime));

        String content = TouTiaoUtils.fetchImage(category, String.valueOf(maxBehotTime), signature, header);
        LOGGER.info("获取结果", content, category, maxBehotTime);

        JSONObject jsonObject = JSON.parseObject(content);

        if (SUCCESS.equals(jsonObject.getString("message"))) {
            JSONArray data = jsonObject.getJSONArray("data");
            String js = JSONObject.toJSONString(data);
            List<ImageNews> list =  JSONObject.parseArray(js, ImageNews.class);
            return list;
        }
        return null;
    }


    @Override
    public ImagesDetail findImageNewsDetails(String uuid) throws ToutiaoException {
        Map<String, String> header = getHeader();

        JSONObject contentObject = TouTiaoUtils.fetchImageDetail(uuid, header);


        ImagesDetail detail = new ImagesDetail();

        return detail;
    }


}
