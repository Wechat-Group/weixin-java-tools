package me.chanjar.weixin.common.api;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class WxMessageInRedisDuplicateCheckerTest {

  private RedissonClient redissonClient;

  @BeforeTest
  public void init() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    config.setTransportMode(TransportMode.NIO);
    this.redissonClient = Redisson.create(config);
    checker = new WxMessageInRedisDuplicateChecker(redissonClient);
    checker.setExpire(2);
  }

  private WxMessageInRedisDuplicateChecker checker;

  public void test() throws InterruptedException {
    Long[] msgIds = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};

    // 第一次检查
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      assertFalse(result);
    }

    // 过1秒再检查
    TimeUnit.SECONDS.sleep(1);
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      assertTrue(result);
    }

    // 过1.5秒再检查
    TimeUnit.MILLISECONDS.sleep(1500L);
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      assertFalse(result);
    }

  }

}
