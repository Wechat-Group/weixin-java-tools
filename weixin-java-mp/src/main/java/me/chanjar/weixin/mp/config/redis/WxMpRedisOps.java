package me.chanjar.weixin.mp.config.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 微信公众号Redis相关操作
 * <p>
 * 请不要直接实现该接口, 该接口承诺稳定
 *
 * @see BaseWxMpRedisOps 实现需要继承该类
 */
public interface WxMpRedisOps {

  String getValue(String key);

  Long getExpire(String key);

  void setValue(String key, String value, int expire, TimeUnit timeUnit);

  void expire(String key, int expire, TimeUnit timeUnit);

  Lock getLock(String key);
}
