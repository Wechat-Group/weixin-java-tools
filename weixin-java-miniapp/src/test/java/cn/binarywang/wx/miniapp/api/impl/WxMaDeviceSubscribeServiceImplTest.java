package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.device.WxMaDeviceSubscribeMessageRequest;
import cn.binarywang.wx.miniapp.bean.device.WxMaDeviceTicketRequest;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 小程序设备订阅消息相关 测试类
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 * @since 2021-12-16 17:13:35
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaDeviceSubscribeServiceImplTest {

  @Inject
  protected WxMaService wxService;

  @Test
  public void testGetSnTicket() throws WxErrorException{
    WxMaDeviceTicketRequest wxMaDeviceTicketRequest = new WxMaDeviceTicketRequest();
    wxMaDeviceTicketRequest.setModelId("11111");
    wxMaDeviceTicketRequest.setSn("11111");
    String snTicket = this.wxService.getDeviceSubscribeService().getSnTicket(wxMaDeviceTicketRequest);
    System.out.println(snTicket);
  }

  @Test
  public void sendDeviceSubscribeMsg() throws WxErrorException{
    WxMaDeviceSubscribeMessageRequest wxMaDeviceSubscribeMessageRequest = new WxMaDeviceSubscribeMessageRequest();
    wxMaDeviceSubscribeMessageRequest.setToOpenidList(Lists.newArrayList("1", "2"));
    wxMaDeviceSubscribeMessageRequest.setPage("pages/index/index");
    wxMaDeviceSubscribeMessageRequest.setTemplateId("11111111");
    wxMaDeviceSubscribeMessageRequest.setSn("111111");
    JsonObject data = GsonParser.parse("{\n" +
      "\t\t\"thing2\": {\n" +
      "\t\t\t\"value\": \"阳台\"\n" +
      "\t\t},\n" +
      "\t\t\"time1\": {\n" +
      "\t\t\t\"value\": \"2021-09-30 13:32:44\"\n" +
      "\t\t},\n" +
      "\t\t\"thing3\": {\n" +
      "\t\t\t\"value\": \"洗衣已完成\"\n" +
      "\t\t}\n" +
      "\t}");
    wxMaDeviceSubscribeMessageRequest.setData(data);
    this.wxService.getDeviceSubscribeService().sendDeviceSubscribeMsg(wxMaDeviceSubscribeMessageRequest);
  }
}
