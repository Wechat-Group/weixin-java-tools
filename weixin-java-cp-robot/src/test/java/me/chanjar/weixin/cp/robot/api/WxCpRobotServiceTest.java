package me.chanjar.weixin.cp.robot.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.robot.api.impl.WxCpRobotServiceImpl;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessageSendResult;
import me.chanjar.weixin.cp.robot.config.impl.WxCpRobotDefaultConfigImpl;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author linfeng-eqxiu
 * @date 2019/12/4
 */
@Test
public class WxCpRobotServiceTest {

  @Test
  public void testSendTextMessage() {
    //https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=2
    WxCpRobotDefaultConfigImpl config = new WxCpRobotDefaultConfigImpl();
    config.setRobotKey("13");  // 设置机器人Key

    WxCpRobotServiceImpl wxCpService = new WxCpRobotServiceImpl();
    wxCpService.setWxCpRobotConfigStorage(config);

    WxCpRobotMessage message = WxCpRobotMessage.TEXT().addMobile("17711367871").content("Hello World W").build();
    try {
      WxCpRobotMessageSendResult result = wxCpService.sendRobotMessage(message);
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testSendImageMessage() {
    WxCpRobotDefaultConfigImpl config = new WxCpRobotDefaultConfigImpl();
    config.setRobotKey("13");  // 设置机器人Key

    WxCpRobotServiceImpl wxCpService = new WxCpRobotServiceImpl();
    wxCpService.setWxCpRobotConfigStorage(config);

    WxCpRobotMessage message;
    try {
      message = WxCpRobotMessage.IMAGE().localFile(new File("D:\\deploy\\contact_u2.jpg")).build();
      WxCpRobotMessageSendResult result = wxCpService.sendRobotMessage(message);
    } catch (WxErrorException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testSendNewsMessage() {
    WxCpRobotDefaultConfigImpl config = new WxCpRobotDefaultConfigImpl();
    config.setRobotKey("13");  // 设置机器人Key

    WxCpRobotServiceImpl wxCpService = new WxCpRobotServiceImpl();
    wxCpService.setWxCpRobotConfigStorage(config);

    WxCpRobotMessage message;
    try {
      NewArticle newArticle = new NewArticle();
      newArticle.setTitle("测试图文消息");
      newArticle.setDescription("测试图文消息");
      newArticle.setPicUrl("http://res1.eqh5.com/Fi2EVYCmZnjI1c1CksdeXH_uVCwe");
      newArticle.setUrl("http://www.eqxiu.com/");
      message = WxCpRobotMessage.NEWS().addArticle(newArticle).build();
      WxCpRobotMessageSendResult result = wxCpService.sendRobotMessage(message);
    } catch (WxErrorException e) {
      e.printStackTrace();
    }
  }

}
