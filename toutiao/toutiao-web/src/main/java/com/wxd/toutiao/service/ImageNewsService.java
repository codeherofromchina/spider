package com.wxd.toutiao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wxd.toutiao.dao.ImageNewsDao;

@Service
public class ImageNewsService {

	@Autowired
	@Qualifier("httpImageNewsDao")
	private ImageNewsDao imageNewsDao;

}
