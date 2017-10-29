package us.wili.jason.weixin.open.api;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by JasonY on 17/10/24.
 */
public class WxOpenInRedisConfigStorage extends WxOpenInMemoryConfigStorage {

  private final static String COMPONENT_VERIFY_TICKET_KEY = "wechat_component_verify_ticket_";
  private final static String COMPONENT_ACCESS_TOKEN_KEY = "wechat_component_access_token_";
  private final static String COMPONENT_PRE_AUTH_CODE_KEY = "wechat_component_pre_auth_code_";
  private final static String COMPONENT_AUTHORIZER_ACCESS_TOKEN_KEY = "wechat_authorizer_access_token_";
  private final static String COMPONENT_JSAPI_TICKET_KEY = "wechat_component_jsapi_ticket_";
  private final static String COMPONENT_CARDAPI_TICKET_KEY = "wechat_component_cardapi_ticket_";

  protected final JedisPool jedisPool;
  protected Integer redisDb;
  protected String componentVerifyTicketKey;
  protected String componentAccessTokenKey;
  protected String componentPreAuthCodeKey;
  protected String authorizerAccessTokenKey;
  protected String jsapiTicketKey;
  protected String cardapiTicketKey;

  public WxOpenInRedisConfigStorage(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  public void setAppId(String appid) {
    super.setAppId(appid);
    this.componentVerifyTicketKey = COMPONENT_VERIFY_TICKET_KEY.concat(appid);
    this.componentAccessTokenKey = COMPONENT_ACCESS_TOKEN_KEY.concat(appid);
    this.componentPreAuthCodeKey = COMPONENT_PRE_AUTH_CODE_KEY.concat(appid);
    this.authorizerAccessTokenKey = COMPONENT_AUTHORIZER_ACCESS_TOKEN_KEY.concat(appid + "_");
    this.jsapiTicketKey = COMPONENT_JSAPI_TICKET_KEY.concat(appid + "_");
    this.cardapiTicketKey = COMPONENT_CARDAPI_TICKET_KEY.concat(appid + "_");
  }

  @Override
  public String getComponentVerifyTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.get(this.componentVerifyTicketKey);
    }
  }

  @Override
  public void updateComponentVerifyTicket(String componentVerifyTicket) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.set(this.componentVerifyTicketKey, componentVerifyTicket);
    }
  }

  @Override
  public String getComponentAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.get(this.componentAccessTokenKey);
    }
  }

  @Override
  public boolean isComponentAccessTokenExpired() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.ttl(this.componentAccessTokenKey) < 2;
    }
  }

  @Override
  public void expireComponentAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.expire(this.componentAccessTokenKey, 0);
    }
  }

  @Override
  public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.setex(this.componentAccessTokenKey, expiresInSeconds - 200, componentAccessToken);
    }
  }

  @Override
  public String getPreAuthCode() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.get(this.componentPreAuthCodeKey);
    }
  }

  @Override
  public boolean isPreAuthCodeExpired() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.ttl(this.componentPreAuthCodeKey) < 2;
    }
  }

  @Override
  public void expirePreAuthCode() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.expire(this.componentPreAuthCodeKey, 0);
    }
  }

  @Override
  public void updatePreAuthCode(String preAuthCode, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.setex(this.componentPreAuthCodeKey, expiresInSeconds - 200, preAuthCode);
    }
  }

  @Override
  public String getAuthorizerAccessToken(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.get(this.authorizerAccessTokenKey.concat(authorizerAppid));
    }
  }

  @Override
  public boolean isAuthorizerAccessTokenExpired(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.ttl(this.authorizerAccessTokenKey.concat(authorizerAppid)) < 2;
    }
  }

  @Override
  public void expireAuthorizerAccessToken(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.expire(this.authorizerAccessTokenKey.concat(authorizerAppid), 0);
    }
  }

  @Override
  public void updateAuthorizerAccessToken(String authorizerAppid, String authorizerAccessToken, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.setex(this.authorizerAccessTokenKey.concat(authorizerAppid), expiresInSeconds - 200, authorizerAccessToken);
    }
  }

  @Override
  public String getJsapiTicket(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.get(this.jsapiTicketKey.concat(authorizerAppid));
    }
  }

  @Override
  public boolean isJsapiTicketExpired(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.ttl(this.jsapiTicketKey.concat(authorizerAppid)) < 2;
    }
  }

  @Override
  public void updateJsapiTicket(String authorizerAppid, String jsapiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.setex(this.jsapiTicketKey.concat(authorizerAppid), expiresInSeconds - 200, jsapiTicket);
    }
  }

  @Override
  public void expireJsapiTicket(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.expire(this.jsapiTicketKey.concat(authorizerAppid), 0);
    }
  }

  @Override
  public String getCardApiTicket(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.get(this.cardapiTicketKey.concat(authorizerAppid));
    }
  }

  @Override
  public boolean isCardApiTicketExpired(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      return jedis.ttl(this.cardapiTicketKey.concat(authorizerAppid)) < 2;
    }
  }

  @Override
  public void expireCardApiTicket(String authorizerAppid) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.expire(this.cardapiTicketKey.concat(authorizerAppid), 0);
    }
  }

  @Override
  public void updateCardApiTicket(String authorizerAppid, String cardApiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.select(redisDb);
      jedis.setex(this.cardapiTicketKey.concat(authorizerAppid), expiresInSeconds - 200, cardApiTicket);
    }
  }

  public Integer getRedisDb() {
    return redisDb;
  }

  public void setRedisDb(Integer redisDb) {
    this.redisDb = redisDb;
  }
}
