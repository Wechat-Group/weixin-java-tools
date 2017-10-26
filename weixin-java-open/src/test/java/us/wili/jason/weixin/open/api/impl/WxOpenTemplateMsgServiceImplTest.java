package us.wili.jason.weixin.open.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import us.wili.jason.weixin.open.api.WxOpenService;
import us.wili.jason.weixin.open.api.test.ApiTestModule;
import us.wili.jason.weixin.open.api.test.TestConfigStorage;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <pre>
 * Created by Binary Wang on 2016-10-14.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@Guice(modules = ApiTestModule.class)
public class WxOpenTemplateMsgServiceImplTest {
  @Inject
  protected WxOpenService wxOpenService;
  @Inject
  protected TestConfigStorage configStorage;

//  @Test(invocationCount = 5, threadPoolSize = 3)
  @Test
  public void testSendTemplateMsg() throws WxErrorException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
      .toUser(configStorage.getOpenid())
      .templateId(configStorage.getTemplateId()).build();
    templateMessage.addWxMpTemplateData(new WxMpTemplateData("first", "发模版消息啦", "#FF00FF"));
    templateMessage.addWxMpTemplateData(new WxMpTemplateData("remark", "结束", "#FF00FF"));
    templateMessage.setUrl(" ");
    String msgId = this.wxOpenService.getTemplateMsgService().sendTemplateMsg(configStorage.getAuthorizerAppid(), templateMessage);
    Assert.assertNotNull(msgId);
    System.out.println(msgId);
  }

  @Test
  public void testGetIndustry() throws Exception {
    final WxMpTemplateIndustry industry = this.wxOpenService.getTemplateMsgService().getIndustry(configStorage.getAuthorizerAppid());
    Assert.assertNotNull(industry);
    System.out.println(industry);
  }

  public void testSetIndustry() throws Exception {
    WxMpTemplateIndustry industry = new WxMpTemplateIndustry(new WxMpTemplateIndustry.Industry("1"),
      new WxMpTemplateIndustry.Industry("04"));
    boolean result = this.wxOpenService.getTemplateMsgService().setIndustry(configStorage.getAuthorizerAppid(), industry);
    Assert.assertTrue(result);
  }

  @Test
  public void testAddTemplate() throws Exception {
    String result = this.wxOpenService.getTemplateMsgService().addTemplate(configStorage.getAuthorizerAppid(), configStorage.getShortTemplateId());
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testGetAllPrivateTemplate() throws Exception {
    List<WxMpTemplate> result = this.wxOpenService.getTemplateMsgService().getAllPrivateTemplate(configStorage.getAuthorizerAppid());
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  public void testDelPrivateTemplate() throws Exception {
    String templateId = "RPcTe7-4BkU5A2J3imC6W0b4JbjEERcJg0whOMKJKIc";
    boolean result = this.wxOpenService.getTemplateMsgService().delPrivateTemplate(configStorage.getAuthorizerAppid(), templateId);
    Assert.assertTrue(result);
  }

}
