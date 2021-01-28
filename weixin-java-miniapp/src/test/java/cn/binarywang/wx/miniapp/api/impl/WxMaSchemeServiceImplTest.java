package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.scheme.WxMaGenerateSchemeRequest;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.time.DateUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @author : cofedream
 * @date : 2021-01-28
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaSchemeServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testGenerate() throws WxErrorException {
    final Date date = DateUtils.addMinutes(new Date(), 20);  //  20分钟后失效
    final long expireTime = date.getTime() / 1000;
    final String generate = this.wxService.getWxMaSchemeService().generate(WxMaGenerateSchemeRequest.newBuilder()
      .jumpWxa(WxMaGenerateSchemeRequest.JumpWxa.newBuilder()
//        .path("/pages/productView/editPhone/editPhone") // 都可以
        .path("pages/productView/editPhone/editPhone") //
        .query("")
        .build())
      .isExpire(true) // 到期失效
      .expireTime(expireTime) // 失效时间
      .build());
    System.out.println("generate:");
    System.out.println(generate);
  }
}
