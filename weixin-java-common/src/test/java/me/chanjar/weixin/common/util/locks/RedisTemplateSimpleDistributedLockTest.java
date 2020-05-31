package me.chanjar.weixin.common.util.locks;

import lombok.SneakyThrows;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.*;

@Test(enabled = false)
public class RedisTemplateSimpleDistributedLockTest {

  RedisTemplateSimpleDistributedLock redisLock;

  StringRedisTemplate redisTemplate;

  AtomicInteger lockCurrentExecuteCounter;

  @BeforeTest
  public void init() {
    JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
    connectionFactory.setHostName("127.0.0.1");
    connectionFactory.setPort(6379);
    connectionFactory.afterPropertiesSet();
    StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
    this.redisTemplate = redisTemplate;
    this.redisLock = new RedisTemplateSimpleDistributedLock(redisTemplate, 60000);
    this.lockCurrentExecuteCounter = new AtomicInteger(0);
  }

  @Test(description = "多线程测试锁排他性")
  public void testLockExclusive() throws InterruptedException {
    int threadSize = 100;
    final CountDownLatch startLatch = new CountDownLatch(threadSize);
    final CountDownLatch endLatch = new CountDownLatch(threadSize);

    for (int i = 0; i < threadSize; i++) {
      new Thread(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
          startLatch.await();

          redisLock.lock();
          assertEquals(lockCurrentExecuteCounter.incrementAndGet(), 1, "临界区同时只能有一个线程执行");
          lockCurrentExecuteCounter.decrementAndGet();
          redisLock.unlock();

          endLatch.countDown();
        }
      }).start();
      startLatch.countDown();
    }
    endLatch.await();
  }

  @Test
  public void testTryLock() throws InterruptedException {
    assertTrue(redisLock.tryLock(3, TimeUnit.SECONDS), "第一次加锁应该成功");
    assertNotNull(redisLock.getLockSecretValue());
    String redisValue = this.redisTemplate.opsForValue().get(redisLock.getKey());
    assertEquals(redisValue, redisLock.getLockSecretValue());

    redisLock.unlock();
    assertNull(redisLock.getLockSecretValue());
    redisValue = this.redisTemplate.opsForValue().get(redisLock.getKey());
    assertNull(redisValue, "释放锁后key会被删除");

    redisLock.unlock();
  }


}

