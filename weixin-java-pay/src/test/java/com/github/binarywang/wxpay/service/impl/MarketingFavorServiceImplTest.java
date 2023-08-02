package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.bean.marketing.enums.StockTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * <pre>
 *  营销工具代金券测试类
 * </pre>
 *
 * @author thinsstar
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class MarketingFavorServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  private final String stockId = "批次id";

  private final String appId = "公众号id";
  private final String openId = "微信openid";

  @Test
  public void testCreateFavorStocksV3() throws WxPayException {
    FavorStocksCreateRequest request = new FavorStocksCreateRequest();
    request.setStockName("测试代金券");
    request.setComment("测试代金券备注");
    request.setBelongMerchant(wxPayService.getConfig().getMchId());
    request.setAvailableBeginTime("2021-02-05T00:00:00.000+08:00");
    request.setAvailableEndTime("2021-03-31T00:00:00.000+08:00");
    request.setNoCash(false);
    request.setStockType(StockTypeEnum.NORMAL);
    request.setOutRequestNo(wxPayService.getConfig().getMchId() + "20210204" + "1234567890");
    //发放规则
    FavorStocksCreateRequest.StockUseRule stockUseRule = new FavorStocksCreateRequest.StockUseRule();
    stockUseRule.setMaxCoupons(5);
    stockUseRule.setMaxCouponsPerUser(5);
    stockUseRule.setNaturalPersonLimit(true);
    stockUseRule.setPreventApiAbuse(false);
    stockUseRule.setMaxAmount(50);
    request.setStockUseRule(stockUseRule);
    //样式设置
//    FavorStocksCreateRequest.PatternInfo patternInfo = new FavorStocksCreateRequest.PatternInfo();
//    request.setPatternInfo(patternInfo);
    //核销规则
    FavorStocksCreateRequest.CouponUseRule couponUseRule = new FavorStocksCreateRequest.CouponUseRule();
    FavorStocksCreateRequest.FixedNormalCoupon fixedNormalCoupon = new FavorStocksCreateRequest.FixedNormalCoupon();
    fixedNormalCoupon.setCouponAmount(10);
    fixedNormalCoupon.setTransactionMinimum(11);
    couponUseRule.setFixedNormalCoupon(fixedNormalCoupon);
    couponUseRule.setCombineUse(true);
    couponUseRule.setAvailableMerchants(Collections.singletonList(wxPayService.getConfig().getMchId()));
    request.setCouponUseRule(couponUseRule);
    FavorStocksCreateResult result = wxPayService.getMarketingFavorService().createFavorStocksV3(request);
    String stockId = result.getStockId();

    log.info("stockId: [{}]", stockId);
  }

  @Test
  public void testCreateFavorCouponsV3() throws WxPayException {
    MarketingFavorService marketingFavorService = new MarketingFavorServiceImpl(wxPayService);
    FavorCouponsCreateRequest request = new FavorCouponsCreateRequest();
    request.setStockCreatorMchid(wxPayService.getConfig().getMchId());
    request.setStockId(stockId);
    request.setAppid(appId);
    request.setOutRequestNo(wxPayService.getConfig().getMchId() + "20210204" + "1234567890");
    FavorCouponsCreateResult result = wxPayService.getMarketingFavorService().createFavorCouponsV3(openId, request);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testStartFavorStocksV3() throws WxPayException {
    FavorStocksSetRequest request = new FavorStocksSetRequest();
    request.setStockCreatorMchid(wxPayService.getConfig().getMchId());
    FavorStocksStartResult result = wxPayService.getMarketingFavorService().startFavorStocksV3(stockId, request);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testQueryFavorStocksV3() throws WxPayException {
    FavorStocksQueryRequest request = new FavorStocksQueryRequest();
    request.setOffset(0);
    request.setLimit(10);
    request.setStockCreatorMchid(wxPayService.getConfig().getMchId());
    FavorStocksQueryResult result = wxPayService.getMarketingFavorService().queryFavorStocksV3(request);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testGetFavorStocksV3() throws WxPayException {
    FavorStocksGetResult result = wxPayService.getMarketingFavorService().getFavorStocksV3(stockId, wxPayService.getConfig().getMchId());

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testGetFavorCouponsV3() throws WxPayException {
    FavorCouponsGetResult result = wxPayService.getMarketingFavorService().getFavorCouponsV3("20387541242", appId, openId);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testGetFavorStocksMerchantsV3() throws WxPayException {
    FavorStocksMerchantsGetResult result = wxPayService.getMarketingFavorService().getFavorStocksMerchantsV3(stockId, wxPayService.getConfig().getMchId(), 0, 50);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testGetFavorStocksItemsV3() throws WxPayException {
    FavorStocksItemsGetResult result = wxPayService.getMarketingFavorService().getFavorStocksItemsV3(stockId, wxPayService.getConfig().getMchId(), 0, 100);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testQueryFavorCouponsV3() throws WxPayException {
    FavorCouponsQueryRequest request = new FavorCouponsQueryRequest();
    request.setAppid(appId);
    request.setOpenid(openId);
    request.setAvailableMchid(wxPayService.getConfig().getMchId());
    FavorCouponsQueryResult result = wxPayService.getMarketingFavorService().queryFavorCouponsV3(request);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testGetFavorStocksUseFlowV3() throws WxPayException {
    FavorStocksFlowGetResult result = wxPayService.getMarketingFavorService().getFavorStocksUseFlowV3(stockId);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testGetFavorStocksRefundFlowV3() throws WxPayException {
    FavorStocksFlowGetResult result = wxPayService.getMarketingFavorService().getFavorStocksRefundFlowV3(stockId);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testSaveFavorCallbacksV3() throws WxPayException {
    FavorCallbacksSaveRequest request = new FavorCallbacksSaveRequest();
    request.setMchid(wxPayService.getConfig().getMchId());
    request.setNotifyUrl("你的回调地址");
    request.setSwitchBool(false);
    FavorCallbacksSaveResult result = wxPayService.getMarketingFavorService().saveFavorCallbacksV3(request);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testPauseFavorStocksV3() throws WxPayException {
    FavorStocksSetRequest request = new FavorStocksSetRequest();
    request.setStockCreatorMchid(wxPayService.getConfig().getMchId());
    FavorStocksPauseResult result = wxPayService.getMarketingFavorService().pauseFavorStocksV3(stockId, request);

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testRestartFavorStocksV3() throws WxPayException {
    FavorStocksSetRequest request = new FavorStocksSetRequest();
    request.setStockCreatorMchid(wxPayService.getConfig().getMchId());
    FavorStocksRestartResult result = wxPayService.getMarketingFavorService().restartFavorStocksV3(stockId, request);

    log.info("result: {}", GSON.toJson(result));
  }
}
