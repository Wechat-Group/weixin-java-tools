package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.internet.WxMaInternetResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 *
 * 服务端网络相关接口测试
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 * @date 2021-09-06
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaInternetServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testGetUserEncryptKey() throws WxErrorException {
    WxMaInternetResponse response = this.wxService.getInternetService().getUserEncryptKey("","","hmac_sha256");
    System.out.println(response);
  }
}
