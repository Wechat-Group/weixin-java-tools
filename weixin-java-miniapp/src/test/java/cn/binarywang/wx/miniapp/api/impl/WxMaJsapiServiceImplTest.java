package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2018/8/5.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaJsapiServiceImplTest {
  @Inject
  private WxMaService wxService;
  @Inject
  private WxMaConfig wxMaConfig;

  @Test
  public void testGetJsapiTicket() throws WxErrorException {
    assertThat(this.wxService.getJsapiService().getJsapiTicket()).isNotBlank();
  }

  @Test
  public void testGetJsapiTicket1() throws WxErrorException {
    assertThat(this.wxService.getJsapiService().getJsapiTicket(true)).isNotBlank();
  }

  @Test
  public void testCreateJsapiSignature() throws WxErrorException {
    final WxJsapiSignature jsapiSignature = this.wxService.getJsapiService().createJsapiSignature("http://www.qq.com");
    System.out.println(jsapiSignature);
    assertThat(jsapiSignature).isNotNull();
  }
}
