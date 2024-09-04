package com.binarywang.solon.wxjava.mp_multi.integration;

import com.binarywang.solon.wxjava.mp_multi.configuration.services.WxMpInJedisConfiguration;
import com.binarywang.solon.wxjava.mp_multi.configuration.services.WxMpInMemoryConfiguration;
import com.binarywang.solon.wxjava.mp_multi.configuration.services.WxMpInRedissonConfiguration;
import com.binarywang.solon.wxjava.mp_multi.properties.WxMpMultiProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxMpMultiPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxMpMultiProperties.class);

    context.beanMake(WxMpInJedisConfiguration.class);
    context.beanMake(WxMpInMemoryConfiguration.class);
    context.beanMake(WxMpInRedissonConfiguration.class);

  }
}
