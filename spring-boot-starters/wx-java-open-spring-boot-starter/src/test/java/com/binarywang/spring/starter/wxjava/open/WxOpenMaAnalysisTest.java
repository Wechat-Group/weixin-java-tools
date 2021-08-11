package com.binarywang.spring.starter.wxjava.open;

import cn.binarywang.wx.miniapp.bean.analysis.WxMaRetainInfo;
import com.binarywang.spring.starter.wxjava.open.config.WxOpenAutoConfiguration;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {WxOpenAutoConfiguration.class, RedisAutoConfiguration.class})
@TestPropertySource(locations = "classpath:application-salus.properties")
public class WxOpenMaAnalysisTest {

  @Autowired
  private WxOpenComponentService wxOpenComponentService;

  @Test
  public void getDailyRetain() throws WxErrorException {
    wxOpenComponentService.getAuthorizerList(0,100);
    DateTime start = new DateTime(2021, 4, 10, 0, 0, 0);
    DateTime end = new DateTime(2021, 4, 10, 0, 0, 0);
    WxMaRetainInfo wxMaRetainInfo = wxOpenComponentService
      .getWxMaServiceByAppid("wx19a9e986c2b20119")
      .getAnalysisService()
      .getDailyRetainInfo(start.toDate(), end.toDate());
    System.out.println(WxOpenGsonBuilder.create().toJson(wxMaRetainInfo));
  }
}
