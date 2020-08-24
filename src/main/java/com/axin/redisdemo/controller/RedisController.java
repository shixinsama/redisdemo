package com.axin.redisdemo.controller;


import com.axin.redisdemo.entity.UserEntity;
import com.axin.redisdemo.utils.RedisUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@RequestMapping("/redis")
@RestController
public class RedisController {
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("selectUser")
    @Cacheable(cacheNames = "UserEntity")
    //@Cacheable(value = "test")
    public UserEntity redisSet(String key) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Long.valueOf(123123123));
        userEntity.setGuid(String.valueOf(123123123));
        userEntity.setName(key);
        userEntity.setAge(String.valueOf(20));
        userEntity.setCreateTime(new Date());
        return userEntity;
    }

    @RequestMapping("selectUser1")
    @Cacheable(cacheNames = "msg")
    //@Cacheable(value = "test")
    public String redisSet1(String key) {
        String msg = key;
        System.out.println("没有缓存，执行搜索后并写入缓存中");
        return msg;
    }

    @RequestMapping("get")
    @Cacheable(cacheNames = "UserEntity")
    public UserEntity redisGet(String key) {
        UserEntity userEntity = (UserEntity) redisUtil.get(key);

        return userEntity;
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }

    @RequestMapping("getExpire")
    public Long getExpire(String key) {
        return redisUtil.getExpire(key);
    }
}
