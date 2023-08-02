package me.chanjar.weixin.common.api;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author jiangby
 * @version 1.0
 * created on  2022/5/26 1:46
 */
@Test
public class WxMessageInMemoryDuplicateCheckerSingletonTest {

  private static WxMessageInMemoryDuplicateCheckerSingleton checkerSingleton = WxMessageInMemoryDuplicateCheckerSingleton.getInstance();

  public void test() throws InterruptedException {
    Long[] msgIds = new Long[]{1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};

    // 第一次检查
    for (Long msgId : msgIds) {
      boolean result = checkerSingleton.isDuplicate(String.valueOf(msgId));
      assertFalse(result);
    }

    // 初始化后1S进行检查 每五秒检查一次，过期时间为15秒，过15秒再检查
    TimeUnit.SECONDS.sleep(15);
    for (Long msgId : msgIds) {
      boolean result = checkerSingleton.isDuplicate(String.valueOf(msgId));
      assertTrue(result);
    }

    // 过6秒再检查
    TimeUnit.SECONDS.sleep(6);
    for (Long msgId : msgIds) {
      boolean result = checkerSingleton.isDuplicate(String.valueOf(msgId));
      assertFalse(result);
    }

  }
}
