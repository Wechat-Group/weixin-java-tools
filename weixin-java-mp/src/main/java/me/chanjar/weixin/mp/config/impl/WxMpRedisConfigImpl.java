package me.chanjar.weixin.mp.config.impl;

import com.sun.istack.internal.NotNull;
import me.chanjar.weixin.mp.config.redis.JedisWxMpRedisOps;
import me.chanjar.weixin.mp.config.redis.WxMpRedisOps;
import me.chanjar.weixin.mp.enums.TicketType;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

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
  private static final String LOCK_KEY = "wx:lock:";

  private final WxMpRedisOps redisOps;

  private String accessTokenKey;
  private String lockKey;

  public WxMpRedisConfigImpl(@NotNull JedisPool jedisPool) {
    this(new JedisWxMpRedisOps(jedisPool));
  }

  public WxMpRedisConfigImpl(@NotNull WxMpRedisOps redisOps) {
    this.redisOps = redisOps;
  }

  /**
   * 每个公众号生成独有的存储key.
   */
  @Override
  public void setAppId(String appId) {
    super.setAppId(appId);
    this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
    this.lockKey = ACCESS_TOKEN_KEY.concat(appId).concat(":");
    accessTokenLock = this.redisOps.getLock(lockKey.concat("accessTokenLock"));
    jsapiTicketLock = this.redisOps.getLock(lockKey.concat("jsapiTicketLock"));
    sdkTicketLock = this.redisOps.getLock(lockKey.concat("sdkTicketLock"));
    cardApiTicketLock = this.redisOps.getLock(lockKey.concat("cardApiTicketLock"));
  }

  private String getTicketRedisKey(TicketType type) {
    return String.format("wx:ticket:key:%s:%s", this.appId, type.getCode());
  }

  @Override
  public String getAccessToken() {
    return redisOps.getValue(this.accessTokenKey);
  }

  @Override
  public boolean isAccessTokenExpired() {
    Long expire = redisOps.getExpire(this.accessTokenKey);
    return expire == null || expire < 2;
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    redisOps.setValue(this.accessTokenKey, accessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
  }

  @Override
  public void expireAccessToken() {
    redisOps.expire(this.accessTokenKey, 0, TimeUnit.SECONDS);
  }

  @Override
  public String getTicket(TicketType type) {
    return redisOps.getValue(this.getTicketRedisKey(type));
  }

  @Override
  public boolean isTicketExpired(TicketType type) {
    return redisOps.getExpire(this.getTicketRedisKey(type)) < 2;
  }

  @Override
  public synchronized void updateTicket(TicketType type, String jsapiTicket, int expiresInSeconds) {
    redisOps.setValue(this.getTicketRedisKey(type), jsapiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
  }

  @Override
  public void expireTicket(TicketType type) {
    redisOps.expire(this.getTicketRedisKey(type), 0, TimeUnit.SECONDS);
  }

}
