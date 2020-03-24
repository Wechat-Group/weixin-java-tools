package me.chanjar.weixin.mp.config.impl;

import me.chanjar.weixin.mp.enums.TicketType;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 基于Redis的微信配置provider.
 *
 * <pre>
 *    使用说明：本实现仅供参考，并不完整，
 *    比如为减少项目依赖，未加入redis分布式锁的实现，如有需要请自行实现。
 * </pre>
 *
 * @author nickwong
 */
@SuppressWarnings("hiding")
public class WxMpRedisConfigImpl extends WxMpDefaultConfigImpl {
  private static final String ACCESS_TOKEN_KEY = "wx:access_token:";

  /**
   * 使用连接池保证线程安全.
   */
  private final JedisPool jedisPool;

  private String accessTokenKey;

  public WxMpRedisConfigImpl(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  /**
   * 每个公众号生成独有的存储key.
   */
  @Override
  public void setAppId(String appId) {
    super.setAppId(appId);
    this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
  }

  private String getTicketRedisKey(TicketType type) {
    return String.format("wx:ticket:key:%s:%s", this.appId, type.getCode());
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
  public String getTicket(TicketType type) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.getTicketRedisKey(type));
    }
  }

  @Override
  public boolean isTicketExpired(TicketType type) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(this.getTicketRedisKey(type)) < 2;
    }
  }

  @Override
  public synchronized void updateTicket(TicketType type, String jsapiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(this.getTicketRedisKey(type), expiresInSeconds - 200, jsapiTicket);
    }
  }

  @Override
  public void expireTicket(TicketType type) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(this.getTicketRedisKey(type), 0);
    }
  }

}
