package cn.binarywang.wx.miniapp.config.impl;

import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis存储的微信小程序配置类
 */
public class WxMaRedisBetterConfigImpl extends WxMaDefaultConfigImpl {
  private static final String ACCESS_TOKEN_KEY_TPL = "%s:access_token:%s";
  private static final String TICKET_KEY_TPL = "%s:ticket:key:%s:%s";
  private static final String LOCK_KEY_TPL = "%s:lock:%s:";

  private final WxRedisOps redisOps;
  private final String keyPrefix;

  private volatile String accessTokenKey;
  private volatile String lockKey;

  public WxMaRedisBetterConfigImpl(WxRedisOps redisOps, String keyPrefix) {
    this.redisOps = redisOps;
    this.keyPrefix = keyPrefix;
  }

  @Override
  public void setAppid(String appId) {
    super.setAppid(appId);
    this.accessTokenKey = String.format(ACCESS_TOKEN_KEY_TPL, this.keyPrefix, appId);
    this.lockKey = String.format(LOCK_KEY_TPL, this.keyPrefix, appId);
    super.accessTokenLock = this.redisOps.getLock(lockKey.concat("accessTokenLock"));
    super.jsapiTicketLock = this.redisOps.getLock(lockKey.concat("jsapiTicketLock"));
    super.cardApiTicketLock = this.redisOps.getLock(lockKey.concat("cardApiTicketLock"));
  }

  //------------------------------------------------------------------------
  // token相关
  //------------------------------------------------------------------------
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

  //------------------------------------------------------------------------
  // ticket相关
  //------------------------------------------------------------------------
  @Override
  public String getJsapiTicket() {
    return doGetTicket(TicketType.JSAPI);
  }

  @Override
  public boolean isJsapiTicketExpired() {
    return doIsTicketExpired(TicketType.JSAPI);
  }

  @Override
  public void expireJsapiTicket() {
    doExpireTicket(TicketType.JSAPI);
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    doUpdateTicket(TicketType.JSAPI, jsapiTicket, expiresInSeconds);
  }

  @Override
  public String getCardApiTicket() {
    return doGetTicket(TicketType.WX_CARD);
  }

  @Override
  public boolean isCardApiTicketExpired() {
    return doIsTicketExpired(TicketType.WX_CARD);
  }

  @Override
  public void expireCardApiTicket() {
    doExpireTicket(TicketType.WX_CARD);
  }

  @Override
  public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    doUpdateTicket(TicketType.WX_CARD, cardApiTicket, expiresInSeconds);
  }

  private String getTicketRedisKey(TicketType type) {
    return String.format(TICKET_KEY_TPL, this.keyPrefix, this.appid, type.getCode());
  }

  private String doGetTicket(TicketType type) {
    return redisOps.getValue(this.getTicketRedisKey(type));
  }

  private boolean doIsTicketExpired(TicketType type) {
    return redisOps.getExpire(this.getTicketRedisKey(type)) < 2;
  }

  private void doUpdateTicket(TicketType type, String ticket, int expiresInSeconds) {
    redisOps.setValue(this.getTicketRedisKey(type), ticket, expiresInSeconds - 200, TimeUnit.SECONDS);
  }

  private void doExpireTicket(TicketType type) {
    redisOps.expire(this.getTicketRedisKey(type), 0, TimeUnit.SECONDS);
  }

}
