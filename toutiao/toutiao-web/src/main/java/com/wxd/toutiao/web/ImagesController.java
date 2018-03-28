package com.wxd.toutiao.web;

import com.wxd.toutiao.comm.ResultStatusEnum;
import com.wxd.toutiao.exception.ToutiaoException;
import com.wxd.toutiao.service.ImageNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxd.toutiao.comm.Result;
import com.wxd.toutiao.domain.ImageNews;

import java.util.List;

/**
 * 图片控制器
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/images")
public class ImagesController {
	private Logger logger = LoggerFactory.getLogger(ImagesController.class);


	@Autowired
	private ImageNewsService imageNewsService;

	/**
	 * 所有图片
	 * 
	 * @param timePoint
	 * @return
	 */
	@RequestMapping("/default")
	public Result<List<ImageNews>> execute(long timePoint) {
		ResultStatusEnum resultStatus = ResultStatusEnum.FAIL;
		try {
			List<ImageNews> data = imageNewsService.findImageNews(null, timePoint);
			if (data!=null && data.size() > 0) {
				return new Result<List<ImageNews>>(data);
			}
			resultStatus = ResultStatusEnum.EMPTY;
		}catch (ToutiaoException ex) {
			logger.error("获取默认图片列表异常",ex,timePoint);
			resultStatus = ex.getResultStatusEnum();
		}
		return new Result<List<ImageNews>>(resultStatus);
	}

	/**
	 * 组图
	 * 
	 * @param timePoint
	 * @return
	 */
	@RequestMapping(value = "/gallery")
	public Result<Object> gallery(long timePoint) {

		return new Result<Object>().setMsg("这里是组图");
	}

	/**
	 * 老照片
	 * 
	 * @return
	 */
	@RequestMapping(value = "/old_picture", method = RequestMethod.POST)
	public Result<Object> oldPicture(long timePoint) {

		return new Result<Object>();
	}

	/**
	 * 摄影集
	 * 
	 * @return
	 */
	@RequestMapping(value = "/photography", method = RequestMethod.POST)
	public Result<Object> photography(long timePoint) {

		return new Result<Object>();
	}

}
