package me.chanjar.weixin.common.api;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class WxMessageInMemoryDuplicateCheckerTest {
  private WxMessageInMemoryDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker(2000L, 1000L);

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
