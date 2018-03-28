package com.wxd.toutiao.util;

/**
 * 用于关键字的锁对象
 * Created by wangxiaodan on 2018/3/28.
 */
public class LockObject {
    private String timestamp;
    private String sign;
    public LockObject(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
