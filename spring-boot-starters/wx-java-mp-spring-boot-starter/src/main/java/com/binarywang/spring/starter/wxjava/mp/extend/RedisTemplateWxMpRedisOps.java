package com.binarywang.spring.starter.wxjava.mp.extend;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.redis.BaseWxMpRedisOps;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
public class RedisTemplateWxMpRedisOps extends BaseWxMpRedisOps {

  private final StringRedisTemplate redisTemplate;

  @Override
  public String getValue(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  @Override
  public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, value, expire, timeUnit);
  }

  @Override
  public Long getExpire(String key) {
    return redisTemplate.getExpire(key);
  }

  @Override
  public void expire(String key, int expire, TimeUnit timeUnit) {
    redisTemplate.expire(key, expire, timeUnit);
  }

  @Override
  public Lock getLock(String key) {
    return new ReentrantLock();
  }
}
