package me.chanjar.weixin.common.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 微信Redis相关操作
 * <p>
 * 该接口不承诺稳定, 外部实现请继承{@link BaseWxRedisOps}
 *
 * @see BaseWxRedisOps 实现需要继承该类
 * @see JedisWxRedisOps jedis实现
 * @see RedissonWxRedisOps redisson实现
 * @see RedisTemplateWxRedisOps redisTemplate实现
 */
public interface WxRedisOps {

  String getValue(String key);

  void setValue(String key, String value, int expire, TimeUnit timeUnit);

  Long getExpire(String key);

  void expire(String key, int expire, TimeUnit timeUnit);

  Lock getLock(String key);
}
