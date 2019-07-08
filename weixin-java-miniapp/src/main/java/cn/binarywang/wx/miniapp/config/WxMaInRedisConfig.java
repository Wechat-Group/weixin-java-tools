package cn.binarywang.wx.miniapp.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import com.github.jedis.lock.JedisLock;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 基于Redis的微信配置provider.
 * 
 * <pre>
 * 需要引入依赖<a href="https://github.com/abelaska/jedis-lock">jedis-lock</a>，才能使用该类。
 * </pre>
 *
 * @author <a href="https://github.com/winter4666">winter</a>
 */
public class WxMaInRedisConfig implements WxMaConfig {
	
  private static final String ACCESS_TOKEN = "accessToken";
  private static final String JSAPI_TICKET = "jsapiTicket";
  private static final String CARD_API_TICKET = "cardApiTicket";
  
  private static final String HASH_VALUE_FIELD = "value";
  private static final String HASH_EXPIRE_FIELD = "expire";
	
  protected JedisPool jedisPool;
  /**
   * 微信小程序唯一id，用于拼接存储到redis时的key，防止key重复
   */
  protected String maId;
	
  protected volatile String msgDataFormat;
  protected volatile String appid;
  protected volatile String secret;
  protected volatile String token;
  protected volatile String aesKey;

  protected volatile String httpProxyHost;
  protected volatile int httpProxyPort;
  protected volatile String httpProxyUsername;
  protected volatile String httpProxyPassword;

  protected Lock accessTokenLock;
  protected Lock jsapiTicketLock;
  protected Lock cardApiTicketLock;

  /**
   * 临时文件目录
   */
  protected volatile File tmpDirFile;

  protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;
  
  private String getRedisKey(String key) {
	StringBuilder redisKey = new StringBuilder("maConfig:");
	if(maId == null) {
	  return redisKey.append(key).toString();	  
	} else {
	  return redisKey.append(maId).append(":").append(key).toString();
	}
  }
  
  private String getValueFromRedis(String key) {
  	Jedis jedis = jedisPool.getResource();
  	try {
      return jedis.hget(getRedisKey(key), HASH_VALUE_FIELD);
  	} finally {
  	  jedis.close();
  	}
  }
	
  private void setValueToRedis(String key, long expiresTime, String value) {
  	Jedis jedis = jedisPool.getResource();
  	try {
  	  Map<String, String> hash = new HashMap<String, String>();
  	  hash.put(HASH_VALUE_FIELD, value);
  	  hash.put(HASH_EXPIRE_FIELD, String.valueOf(expiresTime));
  	  jedis.hmset(getRedisKey(key), hash);
  	} finally {
  	  jedis.close();
  	}
  }
  
  private long getExpireFromRedis(String key) {
  	Jedis jedis = jedisPool.getResource();
  	try {
      String expire = jedis.hget(getRedisKey(key), HASH_EXPIRE_FIELD);
      return expire == null ? 0 : Long.valueOf(expire);
  	} finally {
  	  jedis.close();
  	}
  }
  
  private void setExpire(String key, long expiresTime) {
  	Jedis jedis = jedisPool.getResource();
  	try {
  	  jedis.hset(getRedisKey(key), HASH_EXPIRE_FIELD, String.valueOf(expiresTime));
  	} finally {
  	  jedis.close();
  	}
  }

  public void setJedisPool(JedisPool jedisPool) {
	this.jedisPool = jedisPool;
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
	if(accessTokenLock == null) {
	  synchronized (this) {
		if(accessTokenLock == null) {
		  accessTokenLock = new DistributedLock(getRedisKey("accessTokenLock")); 
		}
	  }
	}
    return accessTokenLock;
  }

  @Override
  public boolean isAccessTokenExpired() {
    return System.currentTimeMillis() >  getExpireFromRedis(ACCESS_TOKEN);
  }

  @Override
  public synchronized void updateAccessToken(WxAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
	setValueToRedis(ACCESS_TOKEN, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L, accessToken);
  }

  @Override
  public String getJsapiTicket() {
    return getValueFromRedis(JSAPI_TICKET);
  }

  @Override
  public Lock getJsapiTicketLock() {
	if(jsapiTicketLock == null) {
	  synchronized (this) {
		if(jsapiTicketLock == null) {
		  jsapiTicketLock = new DistributedLock(getRedisKey("jsapiTicketLock")); 
		}
	  }
	}
    return jsapiTicketLock;
  }

  @Override
  public boolean isJsapiTicketExpired() {
	return System.currentTimeMillis() >  getExpireFromRedis(JSAPI_TICKET);
  }

  @Override
  public void expireJsapiTicket() {
	setExpire(JSAPI_TICKET, 0);
  }

  @Override
  public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
	// 预留200秒的时间
	setValueToRedis(JSAPI_TICKET, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L, jsapiTicket);
  }


  @Override
  public String getCardApiTicket() {
    return getValueFromRedis(CARD_API_TICKET);
  }

  @Override
  public Lock getCardApiTicketLock() {
	if(cardApiTicketLock == null) {
	  synchronized (this) {
		if(cardApiTicketLock == null) {
		  cardApiTicketLock = new DistributedLock(getRedisKey("cardApiTicketLock")); 
		}
	  }
	}
    return cardApiTicketLock;
  }

  @Override
  public boolean isCardApiTicketExpired() {
	return System.currentTimeMillis() >  getExpireFromRedis(CARD_API_TICKET);
  }

  @Override
  public void expireCardApiTicket() {
	setExpire(CARD_API_TICKET, 0);
  }

  @Override
  public void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
	setValueToRedis(CARD_API_TICKET, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L, cardApiTicket);
  }

  @Override
  public void expireAccessToken() {
	setExpire(ACCESS_TOKEN, 0);
  }

  @Override
  public String getSecret() {
    return this.secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  @Override
  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public long getExpiresTime() {
	return getExpireFromRedis(ACCESS_TOKEN);
  }

  @Override
  public String getAesKey() {
    return this.aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  @Override
  public String getMsgDataFormat() {
    return this.msgDataFormat;
  }

  public void setMsgDataFormat(String msgDataFormat) {
    this.msgDataFormat = msgDataFormat;
  }

  @Override
  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return this.httpProxyPort;
  }

  public void setHttpProxyPort(int httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }

  @Override
  public String toString() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
    this.apacheHttpClientBuilder = apacheHttpClientBuilder;
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  @Override
  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }
  
  /**
   * 基于redis的简单分布式锁
   */
  private class DistributedLock implements Lock {
	
	private JedisLock lock;
	
	private DistributedLock(String key) {
	  this.lock = new JedisLock(getRedisKey(key));
	}

	@Override
	public void lock() {
	  Jedis jedis = jedisPool.getResource();
	  try {
		if(!lock.acquire(jedis)) {
		  throw new RuntimeException("acquire timeouted");
		}
	  } catch (InterruptedException e) {
	    throw new RuntimeException("lock failed",e);
	  } finally {
		jedis.close();
	  }
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	  Jedis jedis = jedisPool.getResource();
	  try {
		if(!lock.acquire(jedis)) {
		  throw new RuntimeException("acquire timeouted");
		}
	  } finally {
		jedis.close();
	  }
	}

	@Override
	public boolean tryLock() {
	  Jedis jedis = jedisPool.getResource();
	  try {
		return lock.acquire(jedis);
	  } catch (InterruptedException e) {
	    throw new RuntimeException("lock failed",e);
	  } finally {
		jedis.close();
	  }
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
	  Jedis jedis = jedisPool.getResource();
	  try {
		return lock.acquire(jedis);
	  } finally {
		jedis.close();
	  }
	}

	@Override
	public void unlock() {
	  Jedis jedis = jedisPool.getResource();
	  try {
		lock.release(jedis);
	  } finally {
		jedis.close();
	  }
	}

	@Override
	public Condition newCondition() {
	  throw new RuntimeException("unsupported method");
	}
	  
  }
}
