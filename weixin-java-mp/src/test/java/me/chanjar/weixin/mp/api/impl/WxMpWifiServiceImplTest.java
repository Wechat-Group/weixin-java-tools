package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.wifi.WxMpWifiShopListResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpWifiServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testListShop() throws WxErrorException {
    final WxMpWifiShopListResult result = this.wxService.getWifiService().listShop(1, 2);
    System.out.println(result);
  }
}
