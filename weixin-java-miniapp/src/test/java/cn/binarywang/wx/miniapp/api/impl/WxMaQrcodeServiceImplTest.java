package cn.binarywang.wx.miniapp.api.impl;

import java.io.File;

import org.testng.annotations.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaQrcodeServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testCreateQrCode() throws Exception {
    final File qrCode = this.wxService.getQrcodeService().createQrcode("111", 122);
    System.out.println(qrCode);
  }

  @Test
  public void testCreateWxaCode() throws Exception {
    final File wxCode = this.wxService.getQrcodeService().createWxaCode("111", 122);
    System.out.println(wxCode);
  }

  @Test
  public void testCreateWxaCodeUnlimit() throws Exception {
    final File wxCode = this.wxService.getQrcodeService().createWxaCodeUnlimit("111", null);
    System.out.println(wxCode);
  }

}
