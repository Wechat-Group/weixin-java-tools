package me.chanjar.weixin.mp.api;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 基于Redis的微信配置provider
 * <pre>
 *    使用说明：本实现仅供参考，并不完整，
 *    比如为减少项目依赖，未加入redis分布式锁的实现，如有需要请自行实现。
 * </pre>
 * @author nickwong
 */
@SuppressWarnings("hiding")
public class WxMpInRedisConfigStorage extends WxMpInMemoryConfigStorage {

  private final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

  private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

  private final static String CARDAPI_TICKET_KEY = "wechat_cardapi_ticket_";

  /**
   * 使用连接池保证线程安全
   */
  protected final JedisPool jedisPool;

  private String accessTokenKey;

  private String jsapiTicketKey;

  private String cardapiTicketKey;

  public WxMpInRedisConfigStorage(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  /**
   * 每个公众号生成独有的存储key
   *
   * @param appId
   */
  @Override
  public void setAppId(String appId) {
    super.setAppId(appId);
    this.accessTokenKey = ACCESS_TOKEN_KEY.concat(appId);
    this.jsapiTicketKey = JSAPI_TICKET_KEY.concat(appId);
    this.cardapiTicketKey = CARDAPI_TICKET_KEY.concat(appId);
  }

  @Override
  public String getAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(accessTokenKey);
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
      jedis.setex(accessTokenKey, expiresInSeconds - 200, accessToken);
    }
  }

  @Override
  public void expireAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(accessTokenKey, 0);
    }
  }

  @Override
  public String getJsapiTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(jsapiTicketKey);
    }
  }

  @Override
  public boolean isJsapiTicketExpired() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(jsapiTicketKey) < 2;
    }
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(jsapiTicketKey, expiresInSeconds - 200, jsapiTicket);
    }
  }

  @Override
  public void expireJsapiTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(jsapiTicketKey, 0);
    }
  }

  @Override
  public String getCardApiTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(cardapiTicketKey);
    }
  }

  @Override
  public boolean isCardApiTicketExpired() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(cardapiTicketKey) < 2;
    }
  }

  @Override
  public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(cardapiTicketKey, expiresInSeconds - 200, cardApiTicket);
    }
  }

  @Override
  public void expireCardApiTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(cardapiTicketKey, 0);
    }
  }
}
