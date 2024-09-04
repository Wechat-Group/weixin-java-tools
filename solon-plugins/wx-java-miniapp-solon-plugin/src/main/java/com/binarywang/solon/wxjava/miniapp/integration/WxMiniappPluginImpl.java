package com.binarywang.solon.wxjava.miniapp.integration;

import com.binarywang.solon.wxjava.miniapp.config.WxMaServiceAutoConfiguration;
import com.binarywang.solon.wxjava.miniapp.config.storage.WxMaInJedisConfigStorageConfiguration;
import com.binarywang.solon.wxjava.miniapp.config.storage.WxMaInMemoryConfigStorageConfiguration;
import com.binarywang.solon.wxjava.miniapp.config.storage.WxMaInRedissonConfigStorageConfiguration;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxMiniappPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxMaProperties.class);

    context.beanMake(WxMaServiceAutoConfiguration.class);

    context.beanMake(WxMaInMemoryConfigStorageConfiguration.class);
    context.beanMake(WxMaInJedisConfigStorageConfiguration.class);
    context.beanMake(WxMaInRedissonConfigStorageConfiguration.class);
  }
}
