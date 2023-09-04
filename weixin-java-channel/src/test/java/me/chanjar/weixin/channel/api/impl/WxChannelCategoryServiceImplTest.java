package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import me.chanjar.weixin.channel.api.WxChannelCategoryService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.audit.AuditApplyResponse;
import me.chanjar.weixin.channel.bean.audit.AuditResponse;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.category.CategoryDetailResult;
import me.chanjar.weixin.channel.bean.category.CategoryQualificationResponse;
import me.chanjar.weixin.channel.bean.category.PassCategoryResponse;
import me.chanjar.weixin.channel.bean.category.ShopCategory;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelCategoryServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testListAllCategory() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    CategoryQualificationResponse response = categoryService.listAllCategory();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }

  @Test
  public void testListAvailableCategory() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    List<ShopCategory> shopCategories = categoryService.listAvailableCategory("0");
    assertTrue(CollectionUtils.hasElements(shopCategories));
    shopCategories.forEach(System.out::println);
  }

  @Test
  public void testGetCategoryDetail() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    CategoryDetailResult categoryDetail = categoryService.getCategoryDetail("1602");
    assertNotNull(categoryDetail);
    assertTrue(categoryDetail.isSuccess());
    System.out.println(categoryDetail);
  }

  @Test
  public void testAddCategory() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    List<String> certificates = new ArrayList<>();
    certificates.add(
      "hWNith8iZSrxfN7W2tXOoWSXYWi1qADRJxwImvQl2DC6wqqJrl4g8i/UEZfd59yiiEKAnqy0WETFrOcGZFcJDfpH2ccmNZddzesR1/OpAC7bbfmEiDFBK2QL7MBjhR2m");
    AuditApplyResponse response = categoryService.addCategory("1001", "1002", "1005", certificates);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }

  @Test
  public void testCancelCategoryAudit() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    String auditId = "4403159413";
    WxChannelBaseResponse response = categoryService.cancelCategoryAudit(auditId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }

  @Test
  public void testGetAudit() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    String auditId = "4403159413";
    AuditResponse response = categoryService.getAudit(auditId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }

  @Test
  public void testListPassCategory() throws WxErrorException {
    WxChannelCategoryService categoryService = channelService.getCategoryService();
    PassCategoryResponse response = categoryService.listPassCategory();
    assertNotNull(response);
    assertTrue(response.isSuccess());
    System.out.println(response);
  }
}
