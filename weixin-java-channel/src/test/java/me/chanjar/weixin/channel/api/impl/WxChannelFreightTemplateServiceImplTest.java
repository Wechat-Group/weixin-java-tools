package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.*;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelFreightTemplateService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.freight.FreightTemplate;
import me.chanjar.weixin.channel.bean.freight.TemplateIdResponse;
import me.chanjar.weixin.channel.bean.freight.TemplateInfoResponse;
import me.chanjar.weixin.channel.bean.freight.TemplateListResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelFreightTemplateServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testListTemplate() throws WxErrorException {
    WxChannelFreightTemplateService freightTemplateService = channelService.getFreightTemplateService();
    Integer offset = 0;
    Integer limit = 20;
    TemplateListResponse response = freightTemplateService.listTemplate(offset, limit);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetTemplate() throws WxErrorException {
    WxChannelFreightTemplateService freightTemplateService = channelService.getFreightTemplateService();
    String templateId = "";
    TemplateInfoResponse response = freightTemplateService.getTemplate(templateId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddTemplate() throws WxErrorException {
    WxChannelFreightTemplateService freightTemplateService = channelService.getFreightTemplateService();
    FreightTemplate template = new FreightTemplate();
    // ...
    TemplateIdResponse response = freightTemplateService.addTemplate(template);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateTemplate() throws WxErrorException {
    WxChannelFreightTemplateService freightTemplateService = channelService.getFreightTemplateService();
    FreightTemplate template = new FreightTemplate();
    // ...
    TemplateIdResponse response = freightTemplateService.updateTemplate(template);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
