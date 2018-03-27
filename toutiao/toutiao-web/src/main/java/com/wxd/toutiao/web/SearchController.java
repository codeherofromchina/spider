package com.wxd.toutiao.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxd.toutiao.comm.Result;

/**
 * 图片控制器
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/search")
public class SearchController {

	/**
	 * 综合搜索
	 * 
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/search_tab")
	public Result<Object> searchTab(String keyword, Integer pageNum) {

		return new Result<Object>();
	}

	/**
	 * 视频搜索
	 * 
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/video")
	public Result<Object> video(String keyword, Integer pageNum) {

		return new Result<Object>();
	}

	/**
	 * 图片搜索
	 * 
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/gallery")
	public Result<Object> gallery(String keyword, Integer pageNum) {

		return new Result<Object>();
	}

}
