package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author Mklaus
 * @date 2018-01-22 下午2:02
 */
@Guice(modules = ApiTestModule.class)
public class WxMpSubscribeMsgServiceImplTest {

  @Inject
  protected WxMpService wxService;

  @Test
  public void testSendSubscribeMessage() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();

    WxMpSubscribeMessage message = WxMpSubscribeMessage.builder()
      .title("weixin test")
      .toUser(configStorage.getOpenid())
      .templateId(configStorage.getTemplateId())
      .scene("1000")
      .contentColor("#FF0000")
      .contentValue("Send subscribe message test")
      .build();

    boolean send = this.wxService.getSubscribeMsgService().sendSubscribeMessage(message);
    Assert.assertTrue(send);
  }

}
