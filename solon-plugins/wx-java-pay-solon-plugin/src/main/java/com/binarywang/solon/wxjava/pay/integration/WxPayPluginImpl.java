package com.binarywang.solon.wxjava.pay.integration;

import com.binarywang.solon.wxjava.pay.config.WxPayAutoConfiguration;
import com.binarywang.solon.wxjava.pay.properties.WxPayProperties;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2024/9/2 created
 */
public class WxPayPluginImpl implements Plugin {
  @Override
  public void start(AppContext context) throws Throwable {
    context.beanMake(WxPayProperties.class);

    context.beanMake(WxPayAutoConfiguration.class);
  }
}
