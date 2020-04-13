package me.chanjar.weixin.mp.config.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 微信公众号redis操作基本类
 */
public class BaseWxMpRedisOps implements WxMpRedisOps {

  @Override
  public String getValue(String key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Long getExpire(String key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void expire(String key, int expire, TimeUnit timeUnit) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Lock getLock(String key) {
    throw new UnsupportedOperationException();
  }
}
