package me.chanjar.weixin.mp.api;

import redis.clients.jedis.Jedis;

/**
 * 基于Redis的微信配置provider
 *
 * @author lly835
 */
@SuppressWarnings("hiding")
public class WxMpInRedisConfigStorage extends WxMpInMemoryConfigStorage {

  private final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

  private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

  private final static String CARDAPI_TICKET_KEY = "wechat_cardapi_ticket_";

  protected Jedis jedis;

  @Override
  public String getAccessToken() {
    return jedis.get(ACCESS_TOKEN_KEY.concat(appId));
  }

  @Override
  public boolean isAccessTokenExpired() {
    return jedis.ttl(ACCESS_TOKEN_KEY.concat(appId)) < 2;
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    jedis.set(ACCESS_TOKEN_KEY.concat(appId), accessToken);
    jedis.expire(ACCESS_TOKEN_KEY.concat(appId), expiresInSeconds - 200);
  }

  @Override
  public void expireAccessToken() {
    jedis.expire(ACCESS_TOKEN_KEY.concat(appId), 0);
  }

  @Override
  public String getJsapiTicket() {
    return jedis.get(JSAPI_TICKET_KEY.concat(appId));
  }

  @Override
  public boolean isJsapiTicketExpired() {
    return jedis.ttl(JSAPI_TICKET_KEY.concat(appId)) < 2;
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    jedis.set(JSAPI_TICKET_KEY.concat(appId), jsapiTicket);
    jedis.expire(JSAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200);
  }

  @Override
  public void expireJsapiTicket() {
    jedis.expire(JSAPI_TICKET_KEY.concat(appId), 0);
  }

  /**
   * 卡券api_ticket
   */
  @Override
  public String getCardApiTicket() {
    return jedis.get(CARDAPI_TICKET_KEY.concat(appId));
  }

  @Override
  public boolean isCardApiTicketExpired() {
    return jedis.ttl(CARDAPI_TICKET_KEY.concat(appId)) < 2;
  }

  @Override
  public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    jedis.set(CARDAPI_TICKET_KEY.concat(appId), cardApiTicket);
    jedis.expire(CARDAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200);
  }

  @Override
  public void expireCardApiTicket() {
    jedis.expire(CARDAPI_TICKET_KEY.concat(appId), 0);
  }

  public void setJedis(Jedis jedis) {
    this.jedis = jedis;
  }
}
