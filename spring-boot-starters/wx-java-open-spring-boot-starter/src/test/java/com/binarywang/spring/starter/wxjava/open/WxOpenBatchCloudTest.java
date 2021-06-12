package com.binarywang.spring.starter.wxjava.open;

import com.binarywang.spring.starter.wxjava.open.config.WxOpenAutoConfiguration;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.bean.result.WxOpenMaBatchCloudDescribeEnvResult;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {WxOpenAutoConfiguration.class, RedisAutoConfiguration.class})
@TestPropertySource(locations = "classpath:application-salus.properties")
public class WxOpenBatchCloudTest {

  @Autowired
  private WxOpenComponentService wxOpenComponentService;

  @Test
  public void testCreateEnv() throws WxErrorException {
    WxOpenResult wxOpenResult = wxOpenComponentService.getWxOpenMaBatchCloudService().createEnv("test");
    System.out.println(wxOpenResult.toString());
  }

  @Test
  public void describeEnvs() throws WxErrorException {
    WxOpenMaBatchCloudDescribeEnvResult wxOpenMaBatchCloudDescribeEnvResult = wxOpenComponentService.getWxOpenMaBatchCloudService().describeEnvs();
    System.out.println(wxOpenMaBatchCloudDescribeEnvResult.toString());
  }
}
