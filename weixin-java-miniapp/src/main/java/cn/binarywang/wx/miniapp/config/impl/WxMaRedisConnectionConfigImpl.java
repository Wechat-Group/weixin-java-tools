package cn.binarywang.wx.miniapp.config.impl;

import redis.clients.jedis.Jedis;

/**
 * 基于Redis的微信配置provider. 使用自己管理的 Jedis 实例进行 Redis 操作。
 *
 * <pre>
 * 需要引入依赖<a href="https://github.com/abelaska/jedis-lock">jedis-lock</a>，才能使用该类。
 * </pre>
 */
public class WxMaRedisConnectionConfigImpl extends AbstractWxMaRedisConfig {

  public WxMaRedisConnectionConfigImpl(Jedis jedis) {
    this.jedis = jedis;
  }

  private Jedis jedis;

  @Override
  protected Jedis getJedis() {
    return jedis;
  }
}
