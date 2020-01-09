package me.chanjar.weixin.open.api.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenInRedisConfigStorage extends AbstractWxOpenInRedisConfigStorage {

  protected final Pool<Jedis> jedisPool;

  public WxOpenInRedisConfigStorage(Pool<Jedis> jedisPool) {
    this.jedisPool = jedisPool;
  }

  public WxOpenInRedisConfigStorage(Pool<Jedis> jedisPool, String keyPrefix) {
    this.jedisPool = jedisPool;
    this.keyPrefix = keyPrefix;
  }

  public WxOpenInRedisConfigStorage(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  public String getComponentVerifyTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.componentVerifyTicketKey);
    }
  }

  @Override
  public void setComponentVerifyTicket(String componentVerifyTicket) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.set(this.componentVerifyTicketKey, componentVerifyTicket);
    }
  }

  @Override
  public String getComponentAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.componentAccessTokenKey);
    }
  }

  @Override
  public boolean isComponentAccessTokenExpired() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(this.componentAccessTokenKey) < 2;
    }
  }

  @Override
  public void expireComponentAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(this.componentAccessTokenKey, 0);
    }
  }

  @Override
  public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(this.componentAccessTokenKey, expiresInSeconds - 200, componentAccessToken);
    }
  }

  @Override
  public String getAuthorizerRefreshToken(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.getKey(this.authorizerRefreshTokenKey, appId));
    }
  }

  @Override
  public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.set(this.getKey(this.authorizerRefreshTokenKey, appId), authorizerRefreshToken);
    }
  }

  @Override
  public String getAuthorizerAccessToken(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.getKey(this.authorizerAccessTokenKey, appId));
    }
  }

  @Override
  public boolean isAuthorizerAccessTokenExpired(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(this.getKey(this.authorizerAccessTokenKey, appId)) < 2;
    }
  }

  @Override
  public void expireAuthorizerAccessToken(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(this.getKey(this.authorizerAccessTokenKey, appId), 0);
    }
  }

  @Override
  public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(this.getKey(this.authorizerAccessTokenKey, appId), expiresInSeconds - 200, authorizerAccessToken);
    }
  }

  @Override
  public String getJsapiTicket(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.getKey(this.jsapiTicketKey, appId));
    }
  }

  @Override
  public boolean isJsapiTicketExpired(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(this.getKey(this.jsapiTicketKey, appId)) < 2;
    }
  }

  @Override
  public void expireJsapiTicket(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(this.getKey(this.jsapiTicketKey, appId), 0);
    }
  }

  @Override
  public void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(this.getKey(this.jsapiTicketKey, appId), expiresInSeconds - 200, jsapiTicket);
    }
  }

  @Override
  public String getCardApiTicket(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(this.getKey(this.cardApiTicket, appId));
    }
  }

  @Override
  public boolean isCardApiTicketExpired(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(this.getKey(this.cardApiTicket, appId)) < 2;
    }
  }

  @Override
  public void expireCardApiTicket(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(this.getKey(this.cardApiTicket, appId), 0);
    }
  }

  @Override
  public void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(this.getKey(this.cardApiTicket, appId), expiresInSeconds - 200, cardApiTicket);
    }
  }
}
