package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * <pre>
 *  Created by BinaryWang on 2019/3/29.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxMpServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testSwitchover() {
    assertTrue(this.wxService.switchover("another"));
    assertThat(WxMpConfigStorageHolder.get()).isEqualTo("another");
    assertFalse(this.wxService.switchover("whatever"));
  }

  @Test
  public void testSwitchover1() throws WxErrorException {
    assertThat(this.wxService.switchover1("another").getAccessToken()).isNotEmpty();
    assertThat(WxMpConfigStorageHolder.get()).isEqualTo("another");
  }
}
