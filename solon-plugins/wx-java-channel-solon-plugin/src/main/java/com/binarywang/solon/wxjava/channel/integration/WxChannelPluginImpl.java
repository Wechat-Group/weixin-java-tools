package com.binarywang.solon.wxjava.channel.integration;


import com.binarywang.solon.wxjava.channel.config.WxChannelServiceAutoConfiguration;
import com.binarywang.solon.wxjava.channel.config.storage.WxChannelInJedisConfigStorageConfiguration;
import com.binarywang.solon.wxjava.channel.config.storage.WxChannelInMemoryConfigStorageConfiguration;
import com.binarywang.solon.wxjava.channel.config.storage.WxChannelInRedissonConfigStorageConfiguration;
import com.binarywang.solon.wxjava.channel.properties.WxChannelProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxChannelPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxChannelProperties.class);
    context.beanMake(WxChannelServiceAutoConfiguration.class);

    context.beanMake(WxChannelInMemoryConfigStorageConfiguration.class);
    context.beanMake(WxChannelInJedisConfigStorageConfiguration.class);
    context.beanMake(WxChannelInRedissonConfigStorageConfiguration.class);
  }
}
