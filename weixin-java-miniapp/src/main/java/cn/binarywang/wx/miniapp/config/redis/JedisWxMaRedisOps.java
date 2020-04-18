package cn.binarywang.wx.miniapp.config.redis;

import lombok.AllArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Jedis实现相关操作
 */
@AllArgsConstructor
public class JedisWxMaRedisOps implements WxMaRedisOps {

  private JedisPool jedisPool;

  @Override
  public String getValue(String key) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(key);
    }
  }

  @Override
  public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.psetex(key, timeUnit.toMillis(expire), value);
    }
  }

  @Override
  public Long getExpire(String key) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(key);
    }
  }

  @Override
  public void expire(String key, int expire, TimeUnit timeUnit) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.pexpire(key, timeUnit.toMillis(expire));
    }
  }

  @Override
  public Lock getLock(String key) {
    return new ReentrantLock();
  }
}
