package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.*;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Created by Binary Wang on 2016-10-14.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@Guice(modules = ApiTestModule.class)
public class WxMpTemplateMsgServiceImplTest {
  @Inject
  protected WxMpService wxService;

  @Test(invocationCount = 5, threadPoolSize = 3)
  public void testSendTemplateMsg() throws WxErrorException {
    SimpleDateFormat dateFormat = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss.SSS");
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
      .toUser(configStorage.getOpenid())
      .templateId(configStorage.getTemplateId()).build();
    templateMessage.addWxMpTemplateData(
      new WxMpTemplateData("first", dateFormat.format(new Date()), "#FF00FF"));
    templateMessage.addWxMpTemplateData(
      new WxMpTemplateData("remark", RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
    templateMessage.setUrl(" ");
    String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    Assert.assertNotNull(msgId);
    System.out.println(msgId);
  }

  @Test
  public void testGetIndustry() throws Exception {
    final WxMpTemplateIndustry industry = this.wxService.getTemplateMsgService().getIndustry();
    Assert.assertNotNull(industry);
    System.out.println(industry);
  }

  @Test
  public void testSetIndustry() throws Exception {
    WxMpTemplateIndustry industry = new WxMpTemplateIndustry(new WxMpTemplateIndustry.Industry("1"),
      new WxMpTemplateIndustry.Industry("04"));
    boolean result = this.wxService.getTemplateMsgService().setIndustry(industry);
    Assert.assertTrue(result);
  }

  @Test
  public void testAddTemplate() throws Exception {
    String result = this.wxService.getTemplateMsgService().addTemplate("TM00015");
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testGetAllPrivateTemplate() throws Exception {
    List<WxMpTemplate> result = this.wxService.getTemplateMsgService().getAllPrivateTemplate();
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testDelPrivateTemplate() throws Exception {
    String templateId = "RPcTe7-4BkU5A2J3imC6W0b4JbjEERcJg0whOMKJKIc";
    boolean result = this.wxService.getTemplateMsgService().delPrivateTemplate(templateId);
    Assert.assertTrue(result);
  }

}
