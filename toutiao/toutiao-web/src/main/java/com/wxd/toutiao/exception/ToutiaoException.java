package com.wxd.toutiao.exception;

import com.wxd.toutiao.comm.ResultStatusEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangxiaodan on 2018/3/28.
 */
public class ToutiaoException extends Exception {
    private ResultStatusEnum resultStatusEnum;
    private String msg;

    public ToutiaoException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getMsg());
        this.resultStatusEnum = resultStatusEnum;
    }

    public ToutiaoException(String msg) {
        super(msg);
        this.resultStatusEnum = ResultStatusEnum.FAIL;
    }

    public ResultStatusEnum getResultStatusEnum() {
        return resultStatusEnum;
    }
}
