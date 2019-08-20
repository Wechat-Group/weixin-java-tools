package com.binarywang.spring.starter.wxjava.miniapp.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-10
 */
@AllArgsConstructor
@Configuration
@ConditionalOnClass(WxMaService.class)
@EnableConfigurationProperties(WxMaProperties.class)
@ConditionalOnProperty(prefix = "wx.miniapp", value = "enabled", matchIfMissing = true)
public class WxMaAutoConfiguration {
  private WxMaProperties properties;

  /**
   * 小程序service.
   *
   * @return 小程序service
   */
  @Bean
  @ConditionalOnMissingBean(WxMaService.class)
  public WxMaService service() {
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid(StringUtils.trimToNull(this.properties.getAppid()));
    config.setSecret(StringUtils.trimToNull(this.properties.getSecret()));
    config.setToken(StringUtils.trimToNull(this.properties.getToken()));
    config.setAesKey(StringUtils.trimToNull(this.properties.getAesKey()));
    config.setMsgDataFormat(StringUtils.trimToNull(this.properties.getMsgDataFormat()));

    final WxMaServiceImpl service = new WxMaServiceImpl();
    service.setWxMaConfig(config);
    return service;
  }
}
