package com.wxd.toutiao.config;

import com.wxd.toutiao.util.LockObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Created by wangxiaodan on 2018/3/28.
 */
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private static List<LockObject> lockObjectList = Collections.synchronizedList(new ArrayList<LockObject>());

    public static void addLockObject(LockObject obj) {
        lockObjectList.add(obj);
    }

    public static LockObject removeLockObject(LockObject obj) {
        boolean removeFlag = lockObjectList.remove(obj);
        if (removeFlag) {
            return obj;
        } else {
            return null;
        }
    }

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public Receiver(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    public void receiveMessage(String message) {
        System.out.println(message);
        String[] split = message.split(":");
        if(split.length < 2) {
            return ;
        }

        Iterator<LockObject> iterator = lockObjectList.iterator();
        while (iterator.hasNext()) {
            LockObject obj = iterator.next();

            if (obj.getTimestamp().equals(split[0])) {
                obj.setSign(split[1]);
                synchronized (obj) {
                    obj.notify();
                }
                iterator.remove();
            }
        }

    }

}
