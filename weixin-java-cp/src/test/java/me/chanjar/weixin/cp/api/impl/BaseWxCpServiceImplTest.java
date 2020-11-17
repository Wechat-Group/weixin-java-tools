package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2019/3/31.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxCpServiceImplTest {
  @Inject
  protected WxCpService wxService;

  @Test
  public void testGetAgentJsapiTicket() throws WxErrorException {
    assertThat(this.wxService.getAgentJsapiTicket()).isNotEmpty();
    assertThat(this.wxService.getAgentJsapiTicket(true)).isNotEmpty();
  }

  @Test
  public void testJsCode2Session() throws WxErrorException {
    assertThat(this.wxService.jsCode2Session("111")).isNotNull();
  }

  @Test
  public void testGetProviderToken() throws WxErrorException {
    assertThat(this.wxService.getProviderToken("111","123")).isNotNull();
  }
}
