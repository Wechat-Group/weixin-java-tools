package cn.binarywang.wx.miniapp.config.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 微信小程序Redis相关操作
 * <p>
 * 该接口不承诺稳定, 外部实现请继承{@link BaseWxMaRedisOps}
 *
 * @see BaseWxMaRedisOps 实现需要继承该类
 * @see JedisWxMaRedisOps jedis实现
 */
public interface WxMaRedisOps {

  String getValue(String key);

  void setValue(String key, String value, int expire, TimeUnit timeUnit);

  Long getExpire(String key);

  void expire(String key, int expire, TimeUnit timeUnit);

  Lock getLock(String key);
}
