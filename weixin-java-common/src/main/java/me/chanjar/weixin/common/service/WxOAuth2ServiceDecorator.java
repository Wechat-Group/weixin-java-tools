package me.chanjar.weixin.common.service;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

/**
 * 微信 oauth2服务 装饰器
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@AllArgsConstructor
public class WxOAuth2ServiceDecorator implements WxOAuth2Service {

  @Delegate
  private final WxOAuth2Service wxOAuth2Service;
}
