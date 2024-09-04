package com.binarywang.solon.wxjava.qidian.integration;

import com.binarywang.solon.wxjava.qidian.config.WxQidianServiceAutoConfiguration;
import com.binarywang.solon.wxjava.qidian.config.WxQidianStorageAutoConfiguration;
import com.binarywang.solon.wxjava.qidian.properties.WxQidianProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxQidianPluginImpl implements Plugin{
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxQidianProperties.class);

    context.beanMake(WxQidianStorageAutoConfiguration.class);
    context.beanMake(WxQidianServiceAutoConfiguration.class);
  }
}
