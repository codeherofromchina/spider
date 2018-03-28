package com.wxd.toutiao.dao.impl;

import com.wxd.toutiao.comm.ResultStatusEnum;
import com.wxd.toutiao.config.Receiver;
import com.wxd.toutiao.exception.ToutiaoException;
import com.wxd.toutiao.util.LockObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxiaodan on 2018/3/28.
 */
@Component
public abstract class AbstractTouTiaoDao {
    private Logger logger = LoggerFactory.getLogger(AbstractTouTiaoDao.class);
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
    //@Value("#{toutiao.channel.push}")
    private String toutiaoPushChannel = "toutiaopush";

    private static Map<String, String> TOUTIAO_HEADER = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        // 待完整
    }

    protected Map<String, String> getHeader() throws ToutiaoException {
        if (TOUTIAO_HEADER == null || TOUTIAO_HEADER.size() == 0) {
            throw new ToutiaoException(ResultStatusEnum.SERVER_INIT_ERROR);
        }
        return Collections.unmodifiableMap(TOUTIAO_HEADER);
    }

    /**
     * 根据给定时间戳获取签名
     *
     * @param timestamp
     * @return
     */
    protected String getSignature(String timestamp) throws ToutiaoException {
        LockObject obj = new LockObject(timestamp);
        Receiver.addLockObject(obj);
        synchronized (obj) {
            try {
                stringRedisTemplate.convertAndSend(toutiaoPushChannel, timestamp);
                obj.wait(WAIT_TIME);
                String signature = obj.getSign();
                if (StringUtils.isBlank(signature)) {
                    throw new ToutiaoException(ResultStatusEnum.SERVER_TIMEOUT);
                } else {
                    return signature;
                }
            } catch (InterruptedException e) {
                logger.error("锁异常", e, timestamp);
                throw new ToutiaoException(ResultStatusEnum.SERVER_ERROR);
            } finally {
                Receiver.removeLockObject(obj);
            }
        }
    }

    private final static long WAIT_TIME = 1000 * 10; // 10s

}
