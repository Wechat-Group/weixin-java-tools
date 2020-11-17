package me.chanjar.weixin.common.redis;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import redis.clients.jedis.JedisPool;

public class JedisWxRedisOpsTest extends CommonWxRedisOpsTest {

  JedisPool jedisPool;

  @BeforeTest
  public void init() {
    this.jedisPool = new JedisPool("127.0.0.1", 6379);
    this.wxRedisOps = new JedisWxRedisOps(jedisPool);
  }

  @AfterTest
  public void destroy() {
    this.jedisPool.close();
  }
}
