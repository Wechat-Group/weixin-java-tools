package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.WxChannelWarehouseService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.warehouse.LocationPriorityResponse;
import me.chanjar.weixin.channel.bean.warehouse.PriorityLocationParam;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseIdsResponse;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseLocation;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseParam;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseResponse;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseStockParam;
import me.chanjar.weixin.channel.bean.warehouse.WarehouseStockResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelWarehouseServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testCreateWarehouse() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    WarehouseParam param = new WarehouseParam();
    // ...
    WxChannelBaseResponse response = warehouseService.createWarehouse(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListWarehouse() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    Integer pageSize = 10;
    WarehouseIdsResponse response = warehouseService.listWarehouse(pageSize, null);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWarehouse() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    String warehouseId = "123";
    WarehouseResponse response = warehouseService.getWarehouse(warehouseId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateWarehouse() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    String outWarehouseId = "";
    String name = "";
    String intro = "";
    WxChannelBaseResponse response = warehouseService.updateWarehouse(outWarehouseId, name, intro);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddWarehouseArea() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    String outWarehouseId = "";
    List<WarehouseLocation> coverLocations = new ArrayList<>(4);
    WarehouseLocation location = new WarehouseLocation();
    coverLocations.add(location);
    WxChannelBaseResponse response = warehouseService.addWarehouseArea(outWarehouseId, coverLocations);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteWarehouseArea() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    String outWarehouseId = "";
    List<WarehouseLocation> coverLocations = new ArrayList<>(4);
    WarehouseLocation location = new WarehouseLocation();
    coverLocations.add(location);
    WxChannelBaseResponse response = warehouseService.deleteWarehouseArea(outWarehouseId, coverLocations);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSetWarehousePriority() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    PriorityLocationParam param = new PriorityLocationParam();
    WxChannelBaseResponse response = warehouseService.setWarehousePriority(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWarehousePriority() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    Integer addressId1 = 1;
    Integer addressId2 = 2;
    Integer addressId3 = 3;
    Integer addressId4 = 4;
    LocationPriorityResponse response = warehouseService
      .getWarehousePriority(addressId1, addressId2, addressId3, addressId4);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateWarehouseStock() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    WarehouseStockParam param = new WarehouseStockParam();
    WxChannelBaseResponse response = warehouseService.updateWarehouseStock(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWarehouseStock() throws WxErrorException {
    WxChannelWarehouseService warehouseService = channelService.getWarehouseService();
    String productId = "";
    String skuId = "";
    String outWarehouseId = "";
    WarehouseStockResponse response = warehouseService.getWarehouseStock(productId, skuId, outWarehouseId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
