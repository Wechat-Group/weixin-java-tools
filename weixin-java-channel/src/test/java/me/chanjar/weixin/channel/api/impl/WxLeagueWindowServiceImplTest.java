package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.*;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxLeagueProductService;
import me.chanjar.weixin.channel.api.WxLeagueWindowService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.product.BatchAddParam;
import me.chanjar.weixin.channel.bean.league.product.BatchAddResponse;
import me.chanjar.weixin.channel.bean.league.window.AuthInfoResponse;
import me.chanjar.weixin.channel.bean.league.window.AuthStatusResponse;
import me.chanjar.weixin.channel.bean.league.window.ProductSearchParam;
import me.chanjar.weixin.channel.bean.league.window.WindowProductListResponse;
import me.chanjar.weixin.channel.bean.league.window.WindowProductResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxLeagueWindowServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testAddProduct() throws WxErrorException {
    WxLeagueWindowService leagueWindowService = channelService.getLeagueWindowService();
    String appid = "";
    String openfinderid = "";
    String productId = "";
    WxChannelBaseResponse response = leagueWindowService.addProduct(appid, openfinderid, productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListProduct() throws WxErrorException {
    WxLeagueWindowService leagueWindowService = channelService.getLeagueWindowService();
    ProductSearchParam param = new ProductSearchParam();
    WindowProductListResponse response = leagueWindowService.listProduct(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testRemoveProduct() throws WxErrorException {
    WxLeagueWindowService leagueWindowService = channelService.getLeagueWindowService();
    String appid = "";
    String openfinderid = "";
    String productId = "";
    WxChannelBaseResponse response = leagueWindowService.removeProduct(appid, openfinderid, productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductDetail() throws WxErrorException {
    WxLeagueWindowService leagueWindowService = channelService.getLeagueWindowService();
    String appid = "";
    String openfinderid = "";
    String productId = "";
    WindowProductResponse response = leagueWindowService.getProductDetail(appid, openfinderid, productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWindowAuthInfo() throws WxErrorException {
    WxLeagueWindowService leagueWindowService = channelService.getLeagueWindowService();
    String finderId = "";
    AuthInfoResponse response = leagueWindowService.getWindowAuthInfo(finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWindowAuthStatus() throws WxErrorException {
    WxLeagueWindowService leagueWindowService = channelService.getLeagueWindowService();
    String finderId = "";
    AuthStatusResponse response = leagueWindowService.getWindowAuthStatus(finderId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
