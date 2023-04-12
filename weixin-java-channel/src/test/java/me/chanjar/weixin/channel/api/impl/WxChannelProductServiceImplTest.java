package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelProductService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskAddResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskListResponse;
import me.chanjar.weixin.channel.bean.limit.LimitTaskParam;
import me.chanjar.weixin.channel.bean.product.SkuStockResponse;
import me.chanjar.weixin.channel.bean.product.SpuGetResponse;
import me.chanjar.weixin.channel.bean.product.SpuInfo;
import me.chanjar.weixin.channel.bean.product.SpuListResponse;
import me.chanjar.weixin.channel.bean.product.SpuUpdateResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelProductServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testAddProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    SpuInfo spuInfo = new SpuInfo();
    // ...
    SpuUpdateResponse response = productService.addProduct(spuInfo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    SpuInfo spuInfo = new SpuInfo();
    // ...
    SpuUpdateResponse response = productService.updateProduct(spuInfo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateStock() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    String skuId = "";
    Integer diffType = 1;
    Integer num = 10;
    WxChannelBaseResponse response = productService.updateStock(productId, skuId, diffType, num);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.deleteProduct(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCancelProductAudit() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.cancelProductAudit(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "10000029995861";
    Integer dataType = 3;
    SpuGetResponse response = productService.getProduct(productId, dataType);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    Integer pageSize = 10;
    String nextKey = null;
    Integer status = null;
    SpuListResponse response = productService.listProduct(pageSize, nextKey, status);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.upProduct(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDownProduct() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    WxChannelBaseResponse response = productService.downProduct(productId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetSkuStock() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String productId = "";
    String skuId = "";
    SkuStockResponse response = productService.getSkuStock(productId, skuId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    LimitTaskParam param = new LimitTaskParam();
    // ...
    LimitTaskAddResponse response = productService.addLimitTask(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    Integer pageSize = 1;
    String nextKey = "";
    Integer status = null;
    LimitTaskListResponse response = productService.listLimitTask(pageSize, nextKey, status);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testStopLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String taskId = "";
    WxChannelBaseResponse response = productService.stopLimitTask(taskId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteLimitTask() throws WxErrorException {
    WxChannelProductService productService = channelService.getProductService();
    String taskId = "";
    WxChannelBaseResponse response = productService.deleteLimitTask(taskId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
