package me.chanjar.weixin.common.api;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 利用redis检查消息是否重复
 *
 */
@RequiredArgsConstructor
public class WxMessageInRedisDuplicateChecker implements WxMessageDuplicateChecker {

  /**
   * 过期时间
   */
  private int expire = 10;

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final RedissonClient redissonClient;

  /**
   * messageId是否重复
   *
   * @param messageId messageId
   * @return 是否
   */
  @Override
  public boolean isDuplicate(String messageId) {
    RBucket<String> r = redissonClient.getBucket("wx:message:duplicate:check:" + messageId);
    boolean setSuccess = r.trySet("1", expire, TimeUnit.SECONDS);
    return !setSuccess;
  }

  public int getExpire() {
    return expire;
  }

  public void setExpire(int expire) {
    this.expire = expire;
  }
}
