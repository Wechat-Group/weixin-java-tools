package com.binarywang.solon.wxjava.cp.integration;

import com.binarywang.solon.wxjava.cp.config.WxCpServiceAutoConfiguration;
import com.binarywang.solon.wxjava.cp.properties.WxCpProperties;
import com.binarywang.solon.wxjava.cp.storage.WxCpInJedisConfigStorageConfiguration;
import com.binarywang.solon.wxjava.cp.storage.WxCpInMemoryConfigStorageConfiguration;
import com.binarywang.solon.wxjava.cp.storage.WxCpInRedissonConfigStorageConfiguration;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxCpPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxCpProperties.class);

    context.beanMake(WxCpServiceAutoConfiguration.class);

    context.beanMake(WxCpInMemoryConfigStorageConfiguration.class);
    context.beanMake(WxCpInJedisConfigStorageConfiguration.class);
    context.beanMake(WxCpInRedissonConfigStorageConfiguration.class);
  }
}
