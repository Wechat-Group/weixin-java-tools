package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
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
  public void testSendSubscribeOnceMessage() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();

    WxMpSubscribeMessage message = WxMpSubscribeMessage.builder()
      .title("weixin test")
      .toUser(configStorage.getOpenid())
      .scene("1000")
      .contentColor("#FF0000")
      .contentValue("Send subscribe message test")
      .build();

    try {
      boolean send = this.wxService.getSubscribeMsgService().sendOnce(message);
      Assert.assertTrue(send);
    } catch (WxErrorException e) {
      // 当用户没有授权，获取之前的授权已使用。微信会返回错误代码 {"errcode":43101,"errmsg":"user refuse to accept the msg hint: [xxxxxxxxxxx]"}
      if (e.getError().getErrorCode() != 43101) {
        throw e;
      }
    }

  }

  @Test
  public void testSubscribeMsgAuthorizationUrl() {
  }

  @Test
  public void testGetPubTemplateTitleList() {
  }

  @Test
  public void testGetPubTemplateKeyWordsById() {
  }

  @Test
  public void testAddTemplate() {
  }

  @Test
  public void testGetTemplateList() {
  }

  @Test
  public void testDelTemplate() {
  }

  @Test
  public void testGetCategory() {
  }

  @Test
  public void testSend() {
  }

  @Test
  public void testSendOnce() {
  }
}
