package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shortlink.GenerateShortLinkRequest;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Test
@Guice(modules = ApiTestModule.class)
@Slf4j
public class WxMaLinkServiceImplTest {
  @Inject
  private WxMaService wxMaService;

  @Test
  public void testGenerateUrlLink() throws WxErrorException {
    String url = this.wxMaService.getLinkService().generateUrlLink(GenerateUrlLinkRequest.builder()
      .path("pages/tabBar/home/home")
      .build());

    System.out.println(url);
  }

  @Test
  public void testGenerateShortLink() throws WxErrorException {
    final String generate = this.wxMaService.getLinkService()
      .generateShortLink(GenerateShortLinkRequest.builder().
        pageUrl("pages/productView/editPhone/editPhone?id=31832")
        .pageTitle("productView")
        .isPermanent(false).build());
    System.out.println("generate:");
    System.out.println(generate);
  }

  /**
   * 多版本链接生成测试
   * 开发时,仅支持IOS设备打开体验版及开发版
   */
  @Test
  public void testGenerateMultiEnvUrlLink() throws WxErrorException {
    String url = this.wxMaService.getLinkService().generateUrlLink(GenerateUrlLinkRequest.builder()
      .path("")
      .envVersion("trial")
      .build());
    log.info("generate url link = {}", url);
  }
}
