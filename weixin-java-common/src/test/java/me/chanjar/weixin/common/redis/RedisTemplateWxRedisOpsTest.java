package me.chanjar.weixin.common.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class RedisTemplateWxRedisOpsTest extends CommonWxRedisOpsTest {

  StringRedisTemplate redisTemplate;

  @BeforeTest
  public void init() {
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName("127.0.0.1");
    connectionFactory.setPort(6379);
    connectionFactory.afterPropertiesSet();
    StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
    this.redisTemplate = redisTemplate;
    this.wxRedisOps = new RedisTemplateWxRedisOps(this.redisTemplate);
  }

  @AfterTest
  public void destroy() {
  }
}
