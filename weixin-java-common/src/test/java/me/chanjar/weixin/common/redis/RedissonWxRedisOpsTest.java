package me.chanjar.weixin.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class RedissonWxRedisOpsTest extends CommonWxRedisOpsTest {

  RedissonClient redissonClient;

  @BeforeTest
  public void init() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    config.setTransportMode(TransportMode.NIO);
    this.redissonClient = Redisson.create(config);
    this.wxRedisOps = new RedissonWxRedisOps(this.redissonClient);
  }

  @AfterTest
  public void destroy() {
    this.redissonClient.shutdown();
  }
}
