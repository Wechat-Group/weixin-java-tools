package me.chanjar.weixin.cp.api;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;

/***
 * 测试发送消息
 * @author Daniel Qian
 *
 */
@Test(groups = "customMessageAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpMessageAPITest {

  @Inject
  protected WxCpServiceImpl wxService;

  public void testSendCustomMessage() throws WxErrorException {
    ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage = (ApiTestModule.WxXmlCpInMemoryConfigStorage) this.wxService.configStorage;
    WxCpMessage message1 = new WxCpMessage();
    message1.setAgentId(configStorage.getAgentId());
    message1.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
    message1.setToUser(configStorage.getUserId());
    message1.setContent("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");
    this.wxService.messageSend(message1);

    WxCpMessage message2 = WxCpMessage
            .TEXT()
            .agentId(configStorage.getAgentId())
            .toUser(configStorage.getUserId())
        .content("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>")
            .build();
    this.wxService.messageSend(message2);

  }

}
