package cn.binarywang.wx.miniapp.config.impl;

import me.chanjar.weixin.common.error.WxRuntimeException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author <a href="https://github.com/winter4666">winter</a>
 */
public abstract class AbstractWxMaRedisConfig extends WxMaDefaultConfigImpl {

  public interface JedisConfig {
    Jedis config(Jedis jedis);
  }

  private static final String ACCESS_TOKEN = "accessToken";
  private static final String JSAPI_TICKET = "jsapiTicket";
  private static final String CARD_API_TICKET = "cardApiTicket";

  private static final String HASH_VALUE_FIELD = "value";
  private static final String HASH_EXPIRE_FIELD = "expire";

  /**
   * Redis Key 的前缀，默认为 maConfig
   */
  private String redisKeyPrefix = "maConfig";

  /**
   * 微信小程序唯一id，用于拼接存储到redis时的key，防止key重复.
   */
  private String maId;

  private Lock accessTokenLock;
  private Lock jsapiTicketLock;
  private Lock cardApiTicketLock;

  /**
   * 临时文件目录.
   */
  protected volatile File tmpDirFile;

  /**
   * 对从 JedisPool.getResource() 获取到的每个 Jedis 实例进行配置
   */
  private JedisConfig jedisConfig;

  protected abstract Jedis getJedis();

  private Jedis getConfiguredJedis() {
    Jedis jedis = getJedis();
    if (jedisConfig != null) {
      return jedisConfig.config(jedis);
    } else {
      return jedis;
    }
  }

  private String getRedisKey(String key) {
    StringBuilder redisKey = new StringBuilder(redisKeyPrefix).append(":");
    if (maId == null) {
      return redisKey.append(key).toString();
    } else {
      return redisKey.append(maId).append(":").append(key).toString();
    }
  }

  private String getValueFromRedis(String key) {
    try (Jedis jedis = getConfiguredJedis()) {
      return jedis.hget(getRedisKey(key), HASH_VALUE_FIELD);
    }
  }

  private void setValueToRedis(String key, long expiresTime, String value) {
    try (Jedis jedis = getConfiguredJedis()) {
      Map<String, String> hash = new HashMap<>();
      hash.put(HASH_VALUE_FIELD, value);
      hash.put(HASH_EXPIRE_FIELD, String.valueOf(expiresTime));
      jedis.hmset(getRedisKey(key), hash);
    }
  }

  private long getExpireFromRedis(String key) {
    try (Jedis jedis = getConfiguredJedis()) {
      String expire = jedis.hget(getRedisKey(key), HASH_EXPIRE_FIELD);
      return expire == null ? 0 : Long.parseLong(expire);
    }
  }

  private void setExpire(String key, long expiresTime) {
    try (Jedis jedis = getConfiguredJedis()) {
      jedis.hset(getRedisKey(key), HASH_EXPIRE_FIELD, String.valueOf(expiresTime));
    }
  }

  public void setRedisKeyPrefix(String redisKeyPrefix) {
    this.redisKeyPrefix = redisKeyPrefix;
  }

  public void setJedisConfig(JedisConfig jedisConfig) {
    this.jedisConfig = jedisConfig;
  }

  public void setMaId(String maId) {
    this.maId = maId;
  }

  @Override
  public String getAccessToken() {
    return getValueFromRedis(ACCESS_TOKEN);
  }

  @Override
  public Lock getAccessTokenLock() {
    if (accessTokenLock == null) {
      synchronized (this) {
        if (accessTokenLock == null) {
          accessTokenLock = new DistributedLock(getRedisKey("accessTokenLock"));
        }
      }
    }
    return accessTokenLock;
  }

  @Override
  public boolean isAccessTokenExpired() {
    return isExpired(getExpireFromRedis(ACCESS_TOKEN));
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    setValueToRedis(ACCESS_TOKEN, expiresAheadInMillis(expiresInSeconds), accessToken);
  }

  @Override
  public String getJsapiTicket() {
    return getValueFromRedis(JSAPI_TICKET);
  }

  @Override
  public Lock getJsapiTicketLock() {
    if (jsapiTicketLock == null) {
      synchronized (this) {
        if (jsapiTicketLock == null) {
          jsapiTicketLock = new DistributedLock(getRedisKey("jsapiTicketLock"));
        }
      }
    }
    return jsapiTicketLock;
  }

  @Override
  public boolean isJsapiTicketExpired() {
    return isExpired(getExpireFromRedis(JSAPI_TICKET));
  }

  @Override
  public void expireJsapiTicket() {
    setExpire(JSAPI_TICKET, 0);
  }

  @Override
  public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    setValueToRedis(JSAPI_TICKET, expiresAheadInMillis(expiresInSeconds), jsapiTicket);
  }


  @Override
  public String getCardApiTicket() {
    return getValueFromRedis(CARD_API_TICKET);
  }

  @Override
  public Lock getCardApiTicketLock() {
    if (cardApiTicketLock == null) {
      synchronized (this) {
        if (cardApiTicketLock == null) {
          cardApiTicketLock = new DistributedLock(getRedisKey("cardApiTicketLock"));
        }
      }
    }
    return cardApiTicketLock;
  }

  @Override
  public boolean isCardApiTicketExpired() {
    return isExpired(getExpireFromRedis(CARD_API_TICKET));
  }

  @Override
  public void expireCardApiTicket() {
    setExpire(CARD_API_TICKET, 0);
  }

  @Override
  public void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    setValueToRedis(CARD_API_TICKET, expiresAheadInMillis(expiresInSeconds), cardApiTicket);
  }

  @Override
  public void expireAccessToken() {
    setExpiresTime(0);
  }

  @Override
  public long getExpiresTime() {
    return getExpireFromRedis(ACCESS_TOKEN);
  }

  @Override
  public void setExpiresTime(long expiresTime) {
    setExpire(ACCESS_TOKEN, expiresTime);
  }

  /**
   * 基于redis的简单分布式锁.
   */
  private class DistributedLock implements Lock {

    private final String LOCK_SUCCESS = "OK";

    private final Long RELEASE_SUCCESS = 1L;

    private String lockKey;

    private DistributedLock(String key) {
      this.lockKey = key;
    }

    @Override
    public void lock() {
      try (Jedis jedis = getConfiguredJedis()) {

        if (!acquire(jedis)) {
          throw new WxRuntimeException("acquire timeouted");
        }
      } catch (InterruptedException e) {
        throw new WxRuntimeException("lock failed", e);
      }
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {
      try (Jedis jedis = getConfiguredJedis()) {
        if (!acquire(jedis)) {
          throw new WxRuntimeException("acquire timeouted");
        }
      }
    }

    @Override
    public boolean tryLock() {
      try (Jedis jedis = getConfiguredJedis()) {
        return acquire(jedis);
      } catch (InterruptedException e) {
        throw new WxRuntimeException("lock failed", e);
      }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
      try (Jedis jedis = getConfiguredJedis()) {
        return acquire(jedis);
      }
    }

    @Override
    public void unlock() {
      try (Jedis jedis = getConfiguredJedis()) {
        releaseDistributedLock(jedis);
      }
    }

    @Override
    public Condition newCondition() {
      throw new WxRuntimeException("unsupported method");
    }


    /**
     * 尝试获取锁 有限次数的重试
     *
     * @param jedis
     * @return
     * @throws InterruptedException
     */
    private Boolean acquire(Jedis jedis) throws InterruptedException {
      Integer i = 0;
      do {
        i++;
        boolean locked = tryGetDistributedLock(jedis);
        if (locked) {
          return true;
        } else {
          Thread.sleep(100L);
        }
      } while (i < 20);
      return false;
    }

    /**
     * 尝试获取锁
     *
     * @param jedis
     * @return
     */
    private Boolean tryGetDistributedLock(Jedis jedis) {
      Long millisecondsToExpire = 2L;
      Long threadId = Thread.currentThread().getId();
      String result = jedis.set(this.lockKey, threadId.toString(), SetParams.setParams().nx().px(millisecondsToExpire));
      return LOCK_SUCCESS.equals(result);
    }


    /**
     * 释放分布式锁
     *
     * @param jedis
     * @return 是否释放成功
     */
    private Boolean releaseDistributedLock(Jedis jedis) {
      Long threadId = Thread.currentThread().getId();
      String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
      Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(threadId.toString()));
      return RELEASE_SUCCESS.equals(result);
    }

  }
}
