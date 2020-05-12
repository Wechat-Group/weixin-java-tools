package me.chanjar.weixin.common.redis;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.util.locks.JedisDistributedLock;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RequiredArgsConstructor
public class JedisWxRedisOps implements WxRedisOps {

  private final Pool<Jedis> jedisPool;

  @Override
  public String getValue(String key) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(key);
    }
  }

  @Override
  public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      if (expire <= 0) {
        jedis.set(key, value);
      } else {
        jedis.psetex(key, timeUnit.toMillis(expire), value);
      }
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
    return new JedisDistributedLock(jedisPool, key);
  }
}
