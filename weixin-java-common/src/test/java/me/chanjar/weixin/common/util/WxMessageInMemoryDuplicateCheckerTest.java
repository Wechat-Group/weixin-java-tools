package me.chanjar.weixin.common.util;

import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxMessageInMemoryDuplicateCheckerTest {

  public void test() throws InterruptedException {
    Long[] msgIds = new Long[]{1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l};
    WxMessageInMemoryDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker(2000l, 1000l);

    // 第一次检查
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      Assert.assertFalse(result);
    }

    // 过1秒再检查
    Thread.sleep(1000l);
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      Assert.assertTrue(result);
    }

    // 过1.5秒再检查
    Thread.sleep(1500l);
    for (Long msgId : msgIds) {
      boolean result = checker.isDuplicate(String.valueOf(msgId));
      Assert.assertFalse(result);
    }

  }

}
