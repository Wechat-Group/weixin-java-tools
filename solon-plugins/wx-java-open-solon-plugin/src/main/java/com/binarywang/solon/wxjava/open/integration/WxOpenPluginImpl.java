package com.binarywang.solon.wxjava.open.integration;

import com.binarywang.solon.wxjava.open.config.WxOpenServiceAutoConfiguration;
import com.binarywang.solon.wxjava.open.config.storage.WxOpenInJedisConfigStorageConfiguration;
import com.binarywang.solon.wxjava.open.config.storage.WxOpenInMemoryConfigStorageConfiguration;
import com.binarywang.solon.wxjava.open.config.storage.WxOpenInRedissonConfigStorageConfiguration;
import com.binarywang.solon.wxjava.open.properties.WxOpenProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxOpenPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxOpenProperties.class);

    context.beanMake(WxOpenServiceAutoConfiguration.class);

    context.beanMake(WxOpenInMemoryConfigStorageConfiguration.class);
    context.beanMake(WxOpenInJedisConfigStorageConfiguration.class);
    context.beanMake(WxOpenInRedissonConfigStorageConfiguration.class);
  }
}
