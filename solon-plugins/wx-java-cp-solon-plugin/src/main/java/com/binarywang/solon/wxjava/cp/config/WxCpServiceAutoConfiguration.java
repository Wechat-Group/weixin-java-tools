package com.binarywang.solon.wxjava.cp.config;

import com.binarywang.solon.wxjava.cp.properties.WxCpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 企业微信平台相关服务自动注册
 *
 * @author yl
 * created on  2021/12/6
 */
@Configuration
@RequiredArgsConstructor
public class WxCpServiceAutoConfiguration {
  private final WxCpProperties wxCpProperties;

  @Bean
  @Condition(onMissingBean = WxCpService.class,
             onBean = WxCpConfigStorage.class)
  public WxCpService wxCpService(WxCpConfigStorage wxCpConfigStorage) {
    WxCpService wxCpService = new WxCpServiceImpl();
    wxCpService.setWxCpConfigStorage(wxCpConfigStorage);

    WxCpProperties.ConfigStorage storage = wxCpProperties.getConfigStorage();
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    wxCpService.setRetrySleepMillis(retrySleepMillis);
    wxCpService.setMaxRetryTimes(maxRetryTimes);
    return wxCpService;
  }
}
