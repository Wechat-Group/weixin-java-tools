package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxLeagueSupplierService;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderListParam;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CommissionOrderResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.CoopProductResponse;
import me.chanjar.weixin.channel.bean.league.supplier.FlowListParam;
import me.chanjar.weixin.channel.bean.league.supplier.ShopDetailResponse;
import me.chanjar.weixin.channel.bean.league.supplier.ShopListResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierBalanceResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierFlowDetailResponse;
import me.chanjar.weixin.channel.bean.league.supplier.SupplierFlowListResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxLeagueSupplierServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetBalanceInfo() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    SupplierBalanceResponse response = leagueSupplierService.getBalanceInfo();
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFlowDetail() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    String flowId = "";
    SupplierFlowDetailResponse response = leagueSupplierService.getFlowDetail(flowId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFlowList() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    FlowListParam param = new FlowListParam();
    SupplierFlowListResponse response = leagueSupplierService.getFlowList(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductDetail() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    String productId = "";
    String appId = "";
    CoopProductResponse response = leagueSupplierService.getProductDetail(productId, appId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductList() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    String appId = "";
    Integer pageSize = 10;
    String nextKey = null;
    CoopProductListResponse response = leagueSupplierService.getProductList(appId, pageSize, nextKey);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetCommissionOrder() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    String orderId = "";
    String skuId = "";
    CommissionOrderResponse response = leagueSupplierService.getCommissionOrder(orderId, skuId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetCommissionOrderList() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    CommissionOrderListParam param = new CommissionOrderListParam();
    CommissionOrderListResponse response = leagueSupplierService.getCommissionOrderList(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetShopDetail() throws WxErrorException {
    WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    String appId = "";
    ShopDetailResponse response = leagueSupplierService.getShopDetail(appId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetShopList() throws WxErrorException {
WxLeagueSupplierService leagueSupplierService = channelService.getLeagueSupplierService();
    Integer pageSize = 10;
    String nextKey = null;
    ShopListResponse response = leagueSupplierService.getShopList(pageSize, nextKey);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
