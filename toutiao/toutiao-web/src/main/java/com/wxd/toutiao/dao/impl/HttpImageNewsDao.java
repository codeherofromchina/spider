package com.wxd.toutiao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxd.toutiao.dao.ImageNewsDao;
import com.wxd.toutiao.domain.ImageNews;
import com.wxd.toutiao.domain.ImageNewsDetails;
import com.wxd.toutiao.exception.ToutiaoException;
import com.wxd.toutiao.util.TouTiaoUtils;

@Repository("httpImageNewsDao")
public class HttpImageNewsDao extends AbstractTouTiaoDao implements ImageNewsDao {
    ObjectMapper om = new ObjectMapper();

    @Override
    public List<ImageNews> findImageNews(String category, long maxBehotTime) throws ToutiaoException{
        Map<String, String> header = getHeader();
        String signature = getSignature(String.valueOf(maxBehotTime));

        String html = TouTiaoUtils.fetchImage(category,String.valueOf(maxBehotTime),signature,header);
        System.out.println("----------------------------------");
        System.out.println(html);
        System.out.println("----------------------------------");

        return null;
    }


    @Override
    public ImageNewsDetails findImageNewsDetails(String uuid) {
        return null;
    }


}
