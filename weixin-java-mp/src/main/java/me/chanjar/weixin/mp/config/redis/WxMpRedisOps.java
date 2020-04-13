package me.chanjar.weixin.mp.config.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 微信公众号Redis相关操作
 * <p>
 * 该接口不承诺稳定, 外部实现请继承{@link BaseWxMpRedisOps}
 *
 * @see BaseWxMpRedisOps 实现需要继承该类
 * @see JedisWxMpRedisOps jedis实现
 */
public interface WxMpRedisOps {

  String getValue(String key);

  void setValue(String key, String value, int expire, TimeUnit timeUnit);

  Long getExpire(String key);

  void expire(String key, int expire, TimeUnit timeUnit);

  Lock getLock(String key);
}
