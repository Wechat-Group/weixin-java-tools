package me.chanjar.weixin.mp.api.impl;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.marketing.WxMpUserAction;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-07-14
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpMarketingServiceImplTest {
  @Inject
  protected WxMpService wxService;

  @Test
  public void testAddUserActionSets() {
  }

  @Test
  public void testGetUserActionSets() {
  }

  @Test
  public void testAddUserAction() throws WxErrorException {
    this.wxService.getMarketingService().addUserAction(Lists.newArrayList(new WxMpUserAction()));
  }

  @Test
  public void testGetAdLeads() {
  }
}
