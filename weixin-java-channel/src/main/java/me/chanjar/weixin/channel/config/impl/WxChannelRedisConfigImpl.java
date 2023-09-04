package me.chanjar.weixin.channel.config.impl;

import java.util.concurrent.TimeUnit;
import me.chanjar.weixin.common.redis.WxRedisOps;

/**
 * 基于redis存储的微信视频号小店配置类
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public class WxChannelRedisConfigImpl extends WxChannelDefaultConfigImpl {

  private static final String ACCESS_TOKEN_KEY_TPL = "%s:access_token:%s";
  private static final String LOCK_KEY_TPL = "%s:lock:%s:";

  private final WxRedisOps redisOps;
  private final String keyPrefix;

  private volatile String accessTokenKey;
  private volatile String lockKey;

  public WxChannelRedisConfigImpl(WxRedisOps redisOps, String keyPrefix) {
    this.redisOps = redisOps;
    this.keyPrefix = keyPrefix;
  }

  @Override
  public void setAppid(String appId) {
    super.setAppid(appId);
    this.accessTokenKey = String.format(ACCESS_TOKEN_KEY_TPL, this.keyPrefix, appId);
    this.lockKey = String.format(LOCK_KEY_TPL, this.keyPrefix, appId);
    super.accessTokenLock = this.redisOps.getLock(lockKey.concat("accessTokenLock"));
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


  @Override
  public String toString() {
    return "WxChannelRedisConfigImpl{" +
      "appid='" + appid + '\'' +
      ", token='" + token + '\'' +
      ", accessTokenLock=" + accessTokenLock +
      ", tmpDirFile=" + tmpDirFile +
      ", redisOps=" + redisOps +
      ", keyPrefix='" + keyPrefix + '\'' +
      ", accessTokenKey='" + accessTokenKey + '\'' +
      ", lockKey='" + lockKey + '\'' +
      '}';
  }
}
