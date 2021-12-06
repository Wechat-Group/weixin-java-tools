package com.binarywang.spring.starter.wxjava.cp.storage;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author yl
 * @date 2021/12/6
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpProperties.PREFIX + ".config-storage", name = "type",
  matchIfMissing = true, havingValue = "memory"
)
@RequiredArgsConstructor
public class WxCpInMemoryConfigStorageConfiguration extends AbstractWxCpConfigStorageConfiguration {
  private final WxCpProperties wxCpProperties;

  @Bean
  @ConditionalOnMissingBean(WxCpConfigStorage.class)
  public WxCpConfigStorage wxCpConfigStorage() {
    WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
    return this.config(config, wxCpProperties);
  }
}
