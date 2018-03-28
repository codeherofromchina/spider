package com.wxd.toutiao.service;

import com.wxd.toutiao.comm.ResultStatusEnum;
import com.wxd.toutiao.domain.ImageNews;
import com.wxd.toutiao.exception.ToutiaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wxd.toutiao.dao.ImageNewsDao;

import java.util.List;

@Service
public class ImageNewsService {
	private Logger logger = LoggerFactory.getLogger(ImageNewsService.class);

	@Autowired
	@Qualifier("httpImageNewsDao")
	private ImageNewsDao imageNewsDao;

	/**
	 * 查找图片新闻
	 * @param category
	 * @param maxBehotTime
	 * @return
	 * @throws ToutiaoException
	 */
	public List<ImageNews> findImageNews(String category, long maxBehotTime) throws ToutiaoException {
		try {
			return imageNewsDao.findImageNews(category, maxBehotTime);
		}catch (Exception ex) {
			logger.error("异常",ex,category,maxBehotTime);
			if (ex instanceof ToutiaoException) {
				throw (ToutiaoException) ex;
			} else {
				throw new ToutiaoException(ResultStatusEnum.SERVER_ERROR);
			}
		}
	}




}
