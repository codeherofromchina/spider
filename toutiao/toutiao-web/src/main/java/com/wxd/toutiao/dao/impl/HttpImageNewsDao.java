package com.wxd.toutiao.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wxd.toutiao.comm.ResultStatusEnum;
import com.wxd.toutiao.domain.ImageNews;
import com.wxd.toutiao.domain.ImageNewsDetails;
import com.wxd.toutiao.exception.ToutiaoException;
import com.wxd.toutiao.util.TouTiaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.wxd.toutiao.dao.ImageNewsDao;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("httpImageNewsDao")
public class HttpImageNewsDao extends AbstractTouTiaoDao implements ImageNewsDao {
    ObjectMapper om = new ObjectMapper();

    @Override
    public List<ImageNews> findImageNews(String category, long maxBehotTime) throws ToutiaoException{
        Map<String, String> header = getHeader();
        String signature = getSignature(String.valueOf(maxBehotTime));

        String html = TouTiaoUtils.fetchImage(category,String.valueOf(maxBehotTime),signature);



        return null;
    }


    @Override
    public ImageNewsDetails findImageNewsDetails(String uuid) {
        return null;
    }


}
