package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelBrandService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.audit.AuditApplyResponse;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.brand.BasicBrand;
import me.chanjar.weixin.channel.bean.brand.Brand;
import me.chanjar.weixin.channel.bean.brand.BrandApplyListResponse;
import me.chanjar.weixin.channel.bean.brand.BrandInfoResponse;
import me.chanjar.weixin.channel.bean.brand.BrandListResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelBrandServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testListAllBrand() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();

    BrandListResponse response = brandService.listAllBrand(100, null);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    List<BasicBrand> brands = response.getBrands();
    assertTrue(CollectionUtils.hasElements(brands));
    System.out.println(JsonUtils.encode(response));
  }

  @Test
  public void testAddBrandApply() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();
    Brand brand = new Brand();
    // ...
    AuditApplyResponse response = brandService.addBrandApply(brand);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateBrandApply() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();
    Brand brand = new Brand();
    // ...
    AuditApplyResponse response = brandService.updateBrandApply(brand);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCancelBrandApply() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();
    String brandId = "10000000";
    String auditId = "12345566";
    WxChannelBaseResponse response = brandService.cancelBrandApply(brandId, auditId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteBrandApply() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();
    String brandId = "10000000";
    WxChannelBaseResponse response = brandService.deleteBrandApply(brandId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetBrandApply() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();
    String brandId = "10000000";
    BrandInfoResponse response = brandService.getBrandApply(brandId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListBrandApply() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();
    BrandApplyListResponse response = brandService.listBrandApply(10, null, null);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListValidBrandApply() throws WxErrorException {
    WxChannelBrandService brandService = channelService.getBrandService();
    BrandApplyListResponse response = brandService.listValidBrandApply(10, null);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
