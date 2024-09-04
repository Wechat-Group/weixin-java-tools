package com.binarywang.solon.wxjava.cp_multi.integration;

import com.binarywang.solon.wxjava.cp_multi.configuration.services.WxCpInJedisConfiguration;
import com.binarywang.solon.wxjava.cp_multi.configuration.services.WxCpInMemoryConfiguration;
import com.binarywang.solon.wxjava.cp_multi.configuration.services.WxCpInRedissonConfiguration;
import com.binarywang.solon.wxjava.cp_multi.properties.WxCpMultiProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxCpMultiPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxCpMultiProperties.class);
    context.beanMake(WxCpInJedisConfiguration.class);
    context.beanMake(WxCpInMemoryConfiguration.class);
    context.beanMake(WxCpInRedissonConfiguration.class);
  }
}
