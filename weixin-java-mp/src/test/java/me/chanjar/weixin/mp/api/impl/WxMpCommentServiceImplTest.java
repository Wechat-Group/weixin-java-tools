package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-16
 */

@Test
@Guice(modules = ApiTestModule.class)
public class WxMpCommentServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testOpen() throws WxErrorException {
    this.wxService.getCommentService().open(1, null);
    this.wxService.getCommentService().open(1, 0);
  }
}
