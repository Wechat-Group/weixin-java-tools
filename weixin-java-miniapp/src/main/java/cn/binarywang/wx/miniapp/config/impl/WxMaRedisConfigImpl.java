package cn.binarywang.wx.miniapp.config.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 基于Redis的微信配置provider. 使用连接池 JedisPool 进行 Redis 操作。
 *
 * <pre>
 * 需要引入依赖<a href="https://github.com/abelaska/jedis-lock">jedis-lock</a>，才能使用该类。
 * </pre>
 */
public class WxMaRedisConfigImpl extends AbstractWxMaRedisConfig {

  private JedisPool jedisPool;

  /**
   * JedisPool 在此配置类是必须项，使用 WxMaRedisConfigImpl(JedisPool) 构造方法来构造实例
   */
  @Deprecated
  public WxMaRedisConfigImpl() {
  }

  public WxMaRedisConfigImpl(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  /**
   * 使用 WxMaRedisConfigImpl(JedisPool) 构造方法来设置 JedisPool
   */
  @Deprecated
  public void setJedisPool(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  protected Jedis getJedis() {
    return jedisPool.getResource();
  }
}
