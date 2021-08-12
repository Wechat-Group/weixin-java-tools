package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAuditBrandRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAuditCategoryRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditBrandResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditCategoryResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditResultResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liming1019
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShopAuditServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testAuditBrand() throws WxErrorException {
    WxMaShopAuditBrandRequest request = WxMaShopAuditBrandRequest.builder().build();
    WxMaShopAuditBrandRequest.AuditReqBean auditReqBean = WxMaShopAuditBrandRequest.AuditReqBean.builder().build();

    auditReqBean.setLicense(new ArrayList<String>(Arrays.asList("https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg")));
    auditReqBean.setBrandInfo(WxMaShopAuditBrandRequest.AuditReqBean.BrandInfoBean.builder()
      .brandAuditType(1)
      .trademarkType("29")
      .brandManagementType(2)
      .commodityOriginType(2)
      .brandWording("346225226351203275")
      .saleAuthorization(new ArrayList<String>(Arrays.asList("https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg")))
      .trademarkRegistrationCertificate(new ArrayList<String>(Arrays.asList("https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg")))
      .trademarkChangeCertificate(new ArrayList<String>(Arrays.asList("https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg")))
      .trademarkRegistrant("https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg")
      .trademarkRegistrantNu("1249305")
      .trademarkAuthorizationPeriod("2020-03-25 12:05:25")
      .trademarkRegistrationApplication(new ArrayList<String>(Arrays.asList("https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg")))
      .trademarkApplicant("张三")
      .trademarkApplicationTime("2020-03-25 12:05:25")
      .importedGoodsForm(new ArrayList<String>(Arrays.asList("https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg")))
      .build());
    request.setAuditReq(auditReqBean);

    WxMaShopAuditBrandResponse response = wxService.getShopAuditService().auditBrand(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testAuditCategory() throws WxErrorException {
    WxMaShopAuditCategoryRequest request = WxMaShopAuditCategoryRequest.builder().build();
    WxMaShopAuditCategoryRequest.AuditReqBean auditReqBean = WxMaShopAuditCategoryRequest.AuditReqBean.builder().build();
    auditReqBean.setLicense(new ArrayList<String>(Arrays.asList("www.xxxxx.com")));
    auditReqBean.setCategoryInfo(WxMaShopAuditCategoryRequest.AuditReqBean.CategoryInfoBean.builder()
      .level1(7419)
      .level2(7439)
      .level3(7448)
      .certificate(new ArrayList<String>(Arrays.asList("www.xxxxx.com")))
      .build());
    request.setAuditReq(auditReqBean);
    WxMaShopAuditCategoryResponse response = wxService.getShopAuditService().auditCategory(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetAuditResult() throws WxErrorException {
    WxMaShopAuditResultResponse response = wxService.getShopAuditService().getAuditResult("RQAAAHIOW-QGAAAAveAUYQ");
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetMiniappCertificate1() throws WxErrorException {
    JsonObject response = wxService.getShopAuditService().getMiniappCertificate(1);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetMiniappCertificate2() throws WxErrorException {
    JsonObject response = wxService.getShopAuditService().getMiniappCertificate(2);
    assertThat(response).isNotNull();
  }
}
