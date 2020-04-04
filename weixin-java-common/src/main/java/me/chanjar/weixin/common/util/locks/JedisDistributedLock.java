package me.chanjar.weixin.common.util.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * JedisPool 分布式锁
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class JedisDistributedLock implements Lock {
  private final Pool<Jedis> jedisPool;
  private final JedisLock lock;

  public JedisDistributedLock(Pool<Jedis> jedisPool, String key){
    this.jedisPool = jedisPool;
    this.lock = new JedisLock(key);
  }

  @Override
  public void lock() {
    try (Jedis jedis = jedisPool.getResource()) {
      if (!lock.acquire(jedis)) {
        throw new RuntimeException("acquire timeouted");
      }
    } catch (InterruptedException e) {
      throw new RuntimeException("lock failed", e);
    }
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    try (Jedis jedis = jedisPool.getResource()) {
      if (!lock.acquire(jedis)) {
        throw new RuntimeException("acquire timeouted");
      }
    }
  }

  @Override
  public boolean tryLock() {
    try (Jedis jedis = jedisPool.getResource()) {
      return lock.acquire(jedis);
    } catch (InterruptedException e) {
      throw new RuntimeException("lock failed", e);
    }
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    try (Jedis jedis = jedisPool.getResource()) {
      return lock.acquire(jedis);
    }
  }

  @Override
  public void unlock() {
    try (Jedis jedis = jedisPool.getResource()) {
      lock.release(jedis);
    }
  }

  @Override
  public Condition newCondition() {
    throw new RuntimeException("unsupported method");
  }

}
