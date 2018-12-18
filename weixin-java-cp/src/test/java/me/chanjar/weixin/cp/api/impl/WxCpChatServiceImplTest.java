package me.chanjar.weixin.cp.api.impl;

import java.util.Arrays;

import me.chanjar.weixin.cp.bean.WxCpChat;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;

/**
 * 测试群聊服务
 *
 * @author gaigeshen
 */
@Guice(modules = ApiTestModule.class)
public class WxCpChatServiceImplTest {

  @Inject
  private WxCpService wxCpService;
  
  @Test
  public void create() throws Exception {
    wxCpService.getChatService().chatCreate("测试群聊", "gaige_shen", Arrays.asList("gaige_shen", "ZhangXiaoMing"), "mychatid");
  }

  @Test
  public void get() throws Exception {
    WxCpChat chat = wxCpService.getChatService().chatGet("mychatid");
    System.out.println(chat);
    Assert.assertEquals(chat.getName(), "测试群聊");
  }

  @Test
  public void update() throws Exception {
    wxCpService.getChatService().chatUpdate("mychatid",  "", "", Arrays.asList("ZhengWuYao"), null);
    WxCpChat chat = wxCpService.getChatService().chatGet("mychatid");
    System.out.println(chat);
    Assert.assertEquals(chat.getUsers().size(), 3);
  }

}
