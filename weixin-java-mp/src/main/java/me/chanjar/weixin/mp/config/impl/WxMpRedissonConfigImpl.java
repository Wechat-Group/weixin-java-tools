package me.chanjar.weixin.mp.config.impl;

import lombok.NonNull;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.redisson.api.RedissonClient;

/**
 * @author wuxingye
 * @date 2020/6/11
 */
public class WxMpRedissonConfigImpl extends WxMpRedisConfigImpl {

  public WxMpRedissonConfigImpl(@NonNull RedissonClient redissonClient, String keyPrefix) {
    this(new RedissonWxRedisOps(redissonClient), keyPrefix);
  }

  public WxMpRedissonConfigImpl(@NonNull RedissonClient redissonClient) {
    this(redissonClient, null);
  }

  private WxMpRedissonConfigImpl(@NonNull WxRedisOps redisOps, String keyPrefix) {
    super(redisOps, keyPrefix);
  }
}
