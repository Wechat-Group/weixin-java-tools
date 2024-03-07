package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.internet.WxMaInternetResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 服务端网络相关接口测试
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 * created on  2021-09-06
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaInternetServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testGetUserEncryptKey2() throws Exception {
    String openid = "ogu-84hVFTbTt-myGisQESoDJ6BM";
    String sessionKey = "9ny8n3t0KULoi0deF7T9pw==";
    WxMaInternetResponse response = this.wxService.getInternetService().getUserEncryptKey(openid, sessionKey);
    assertThat(response).isNotNull();
  }
}
