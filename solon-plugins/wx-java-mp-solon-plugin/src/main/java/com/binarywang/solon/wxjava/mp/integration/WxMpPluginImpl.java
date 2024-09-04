package com.binarywang.solon.wxjava.mp.integration;

import com.binarywang.solon.wxjava.mp.config.WxMpServiceAutoConfiguration;
import com.binarywang.solon.wxjava.mp.config.WxMpStorageAutoConfiguration;
import com.binarywang.solon.wxjava.mp.properties.WxMpProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxMpPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxMpProperties.class);

    context.beanMake(WxMpStorageAutoConfiguration.class);
    context.beanMake(WxMpServiceAutoConfiguration.class);
  }
}
