package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.*;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxLeagueProductService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.product.BatchAddParam;
import me.chanjar.weixin.channel.bean.league.product.BatchAddResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductDetailParam;
import me.chanjar.weixin.channel.bean.league.product.ProductDetailResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductListParam;
import me.chanjar.weixin.channel.bean.league.product.ProductListResponse;
import me.chanjar.weixin.channel.bean.league.product.ProductUpdateParam;
import me.chanjar.weixin.channel.bean.league.product.ProductUpdateResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxLeagueProductServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testBatchAddProduct() throws WxErrorException {
    WxLeagueProductService leagueProductService = channelService.getLeagueProductService();
    BatchAddParam param = new BatchAddParam();
    BatchAddResponse response = leagueProductService.batchAddProduct(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateProduct() throws WxErrorException {
    WxLeagueProductService leagueProductService = channelService.getLeagueProductService();
    ProductUpdateParam param = new ProductUpdateParam();
    ProductUpdateResponse response = leagueProductService.updateProduct(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteProduct() throws WxErrorException {
    WxLeagueProductService leagueProductService = channelService.getLeagueProductService();
    Integer type = 1;
    String productId = "";
    WxChannelBaseResponse response = leagueProductService.deleteProduct(type, productId, null);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProductDetail() throws WxErrorException {
    WxLeagueProductService leagueProductService = channelService.getLeagueProductService();
    ProductDetailParam param = new ProductDetailParam();
    ProductDetailResponse response = leagueProductService.getProductDetail(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListProduct() throws WxErrorException {
    WxLeagueProductService leagueProductService = channelService.getLeagueProductService();
    ProductListParam param = new ProductListParam();
    ProductListResponse response = leagueProductService.listProduct(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
