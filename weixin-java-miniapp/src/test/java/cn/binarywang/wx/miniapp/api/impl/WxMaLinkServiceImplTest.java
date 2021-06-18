package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaLinkServiceImplTest {

  @Inject
  private WxMaService wxMaService;

  @Test
  public void testGenerate() throws WxErrorException {

    GenerateUrlLinkRequest request = GenerateUrlLinkRequest.builder()
      .path("pages/tabBar/home/home")
      .build();

    String url = this.wxMaService.getLinkService().generate(request);

    System.out.println(url);
  }
}
