package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.WxCpUserDetail;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2018/4/22.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpOAuth2ServiceImplTest {
  @Inject
  private WxCpService wxService;

  @Test
  public void testGetUserDetail() throws WxErrorException {
    WxCpUserDetail userDetail = this.wxService.getOauth2Service().getUserDetail("b");
    System.out.println(userDetail);
  }

  @Test
  public void testGetUserInfo() throws WxErrorException {
    final WxCpOauth2UserInfo result = this.wxService.getOauth2Service().getUserInfo("abc");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBuildAuthorizationUrl() {
  }

}
