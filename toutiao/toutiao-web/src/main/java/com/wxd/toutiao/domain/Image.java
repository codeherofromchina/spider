package com.wxd.toutiao.domain;

/**
 * 商品新闻详情信息实体
 * Created by wangxiaodan on 2018/3/28.
 */
public class Image {

    private String url;
    private int width ;
    private int height;
    private String describe;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
