package cn.binarywang.wx.miniapp.config.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 基于Redis的微信配置provider. 使用连接池 JedisPool 进行 Redis 操作。
 *
 * <pre>
 * 需要引入依赖<a href="https://github.com/abelaska/jedis-lock">jedis-lock</a>，才能使用该类。
 * </pre>
 *
 * @author <a href="https://github.com/winter4666">winter</a>
 */
public class WxMaRedisConfigImpl extends AbstractWxMaRedisConfig {

  private JedisPool jedisPool;

  private static final String ACCESS_TOKEN_KEY = "wa:access_token:";

  private String accessTokenKey;

  /**
   * JedisPool 在此配置类是必须项，使用 WxMaRedisConfigImpl(JedisPool) 构造方法来构造实例
   */
  @Deprecated
  public WxMaRedisConfigImpl() {
  }

  public WxMaRedisConfigImpl(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  /**
   * 使用 WxMaRedisConfigImpl(JedisPool) 构造方法来设置 JedisPool
   */
  @Deprecated
  public void setJedisPool(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  protected Jedis getJedis() {
    return jedisPool.getResource();
  }

  /**
   * 每个公众号生成独有的存储key.
   */
  @Override
  public void setAppid(String appId) {
    super.setAppid(appId);
    this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
  }

  @Override
  public String getAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.accessTokenKey);
    }
  }

  @Override
  public boolean isAccessTokenExpired() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(accessTokenKey) < 2;
    }
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(this.accessTokenKey, expiresInSeconds - 200, accessToken);
    }
  }

  @Override
  public void expireAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(this.accessTokenKey, 0);
    }
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
