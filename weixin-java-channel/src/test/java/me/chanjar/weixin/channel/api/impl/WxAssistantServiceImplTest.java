package me.chanjar.weixin.channel.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.window.request.AddWindowProductRequest;
import me.chanjar.weixin.channel.bean.window.request.GetWindowProductListRequest;
import me.chanjar.weixin.channel.bean.window.request.WindowProductRequest;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author <a href="https://github.com/imyzt">imyzt</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxAssistantServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testAddWindowProduct() throws WxErrorException {
    AddWindowProductRequest req = new AddWindowProductRequest();
    req.setProductId("123");
    req.setAppid(channelService.getConfig().getAppid());
    req.setIsHideForWindow(true);
    WxChannelBaseResponse response = channelService.getAssistantService().addWindowProduct(req);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWindowProduct() throws WxErrorException {
    WindowProductRequest req = new WindowProductRequest();
    req.setProductId("123");
    req.setAppid(channelService.getConfig().getAppid());
    WxChannelBaseResponse response = channelService.getAssistantService().getWindowProduct(req);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWindowProductList() throws WxErrorException {
    GetWindowProductListRequest req = new GetWindowProductListRequest();
    req.setAppid(channelService.getConfig().getAppid());
    WxChannelBaseResponse response = channelService.getAssistantService().getWindowProductList(req);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testOffWindowProduct() throws WxErrorException {
    WindowProductRequest req = new WindowProductRequest();
    req.setProductId("123");
    req.setAppid(channelService.getConfig().getAppid());
    WxChannelBaseResponse response = channelService.getAssistantService().offWindowProduct(req);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
