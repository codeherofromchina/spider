package com.wxd.toutiao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxd.toutiao.comm.Result;
import com.wxd.toutiao.domain.ImageNews;

/**
 * 图片控制器
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/images")
public class ImagesController {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 所有图片
	 * 
	 * @param timePoint
	 * @return
	 */
	@RequestMapping("/default")
	public Result<ImageNews> execute(long timePoint) {
		stringRedisTemplate.opsForValue().set("abc", "def");
		return new Result<ImageNews>();
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
