package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.bean.marketing.busifavor.CouponAvailableTime;
import com.github.binarywang.wxpay.bean.marketing.busifavor.CouponUseRule;
import com.github.binarywang.wxpay.bean.marketing.busifavor.FixedNormalCoupon;
import com.github.binarywang.wxpay.bean.marketing.busifavor.StockSendRule;
import com.github.binarywang.wxpay.bean.marketing.enums.StockTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

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
public class MarketingBusiFavorServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  private final String stockId = "1252430000000013";

  private final String appId = "wxb3d189e6a9160863";
  private final String openId = "o3zqj1XFQBg4ju-cMs0AOqVYG0ow";

  @Test
  public void testCreateFavorStocksV3() throws WxPayException {
    BusiFavorStocksCreateRequest request = new BusiFavorStocksCreateRequest();
    request.setStockName("买价值984元3大罐送价值316元2小罐");
    request.setBelongMerchant(wxPayService.getConfig().getMchId());
    request.setComment("买价值984元3大罐送价值316元2小罐");
    request.setGoodsName("仅供安满品牌商品使用");
    request.setCouponCodeMode("WECHATPAY_MODE");
    request.setOutRequestNo(wxPayService.getConfig().getMchId() + "20210204" + "1234567891");

    //核销规则
    CouponUseRule couponUseRule = new CouponUseRule();

    //线下核销
    couponUseRule.setUseMethod("OFF_LINE");

    //券可核销时间
    CouponAvailableTime couponAvailableTime = new CouponAvailableTime();
    couponAvailableTime.setAvailableBeginTime("2021-05-20T13:29:35+08:00");
    couponAvailableTime.setAvailableEndTime("2021-05-21T13:29:35+08:00");
    couponUseRule.setCouponAvailableTime(couponAvailableTime);

    //固定面额满减券
    request.setStockType(StockTypeEnum.NORMAL);
    FixedNormalCoupon fixedNormalCoupon = new FixedNormalCoupon();
    fixedNormalCoupon.setDiscountAmount(31600);
    fixedNormalCoupon.setTransactionMinimum(98400);
    couponUseRule.setFixedNormalCoupon(fixedNormalCoupon);
    request.setCouponUseRule(couponUseRule);

    //发放规则
    StockSendRule stockSendRule = new StockSendRule();
    stockSendRule.setMaxCoupons(100);
    stockSendRule.setMaxCouponsPerUser(5);
    request.setStockSendRule(stockSendRule);

    BusiFavorStocksCreateResult result = wxPayService.getMarketingBusiFavorService().createBusiFavorStocksV3(request);
    String stockId = result.getStockId();

    log.info("stockId: [{}]", stockId);
  }

  @Test
  public void testGetBusiFavorStocksV3() throws WxPayException {
    BusiFavorStocksGetResult result = wxPayService.getMarketingBusiFavorService().getBusiFavorStocksV3("1252430000000012");

    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testVerifyBusiFavorCouponsUseV3() throws WxPayException {
    BusiFavorCouponsUseRequest request = new BusiFavorCouponsUseRequest();
    request.setCouponCode("sxxe34343434");
    request.setAppId("wx1234567889999");
    request.setUseTime("2015-05-20T13:29:35+08:00");
    request.setUseRequestNo("1002600620019090123143254435");

    BusiFavorCouponsUseResult result = wxPayService.getMarketingBusiFavorService().verifyBusiFavorCouponsUseV3(request);
    log.info("result: {}", GSON.toJson(result));
  }

  @Test
  public void testBuildBusiFavorCouponinfoUrl() throws WxPayException {
    BusiFavorCouponsUrlRequest request = new BusiFavorCouponsUrlRequest();
    request.setOpenid(openId);
    request.setOutRequestNo("100002322019090134242");
    request.setSendCouponMerchant("1466573302");
    request.setStockId(stockId);

    String result = wxPayService.getMarketingBusiFavorService().buildBusiFavorCouponinfoUrl(request);
    log.info("result: {}", result);
  }

  @Test
  public void testQueryBusiFavorUsersCoupons() throws WxPayException {
    BusiFavorQueryUserCouponsRequest request = new BusiFavorQueryUserCouponsRequest();

    request.setOpenid(openId);
    request.setAppid(appId);
    request.setStockId("9865000");
    request.setCouponState("USED");
    request.setCreatorMerchant("1466573302");
    BusiFavorQueryUserCouponsResult result = wxPayService.getMarketingBusiFavorService().queryBusiFavorUsersCoupons(request);
    log.info("result: {}", result);
  }

  @Test
  public void testQueryOneBusiFavorUsersCoupons() throws WxPayException {
    BusiFavorQueryOneUserCouponsRequest request = new BusiFavorQueryOneUserCouponsRequest();

    request.setOpenid(openId);
    request.setAppid(appId);
    request.setCouponCode("123446565767");
    BusiFavorQueryOneUserCouponsResult result = wxPayService.getMarketingBusiFavorService().queryOneBusiFavorUsersCoupons(request);
    log.info("result: {}", result);
  }

  @Test
  public void testUploadBusiFavorCouponCodes() throws WxPayException {
    BusiFavorCouponCodeRequest request = new BusiFavorCouponCodeRequest();
    request.setCouponCodeList(Lists.newArrayList("123"));
    request.setUploadRequestNo("upload_request_no");
    BusiFavorCouponCodeResult result = wxPayService.getMarketingBusiFavorService().uploadBusiFavorCouponCodes("98065001", request);
    log.info("result: {}", result);
  }

  @Test
  public void testCreateBusiFavorCallbacks() throws WxPayException {
    BusiFavorCallbacksRequest request = new BusiFavorCallbacksRequest();
    request.setMchid(wxPayService.getConfig().getMchId());
    request.setNotifyUrl("https://ww.sd");
    BusiFavorCallbacksResult result = wxPayService.getMarketingBusiFavorService().createBusiFavorCallbacks(request);
    log.info("result: {}", result);
  }

  @Test
  public void testQueryBusiFavorCallbacks() throws WxPayException {
    BusiFavorCallbacksRequest request = new BusiFavorCallbacksRequest();
    request.setMchid(wxPayService.getConfig().getMchId());
    BusiFavorCallbacksResult result = wxPayService.getMarketingBusiFavorService().queryBusiFavorCallbacks(request);
    log.info("result: {}", result);
  }

  @Test
  public void testQueryBusiFavorCouponsAssociate() throws WxPayException {
    BusiFavorCouponsAssociateRequest request = new BusiFavorCouponsAssociateRequest();
    request.setStockId("100088");
    request.setCouponCode("sxxe34343434");
    request.setOutTradeNo("MCH_102233445");
    request.setOutRequestNo("1002600620019090123143254435");
    BusiFavorCouponsAssociateResult result = wxPayService.getMarketingBusiFavorService().queryBusiFavorCouponsAssociate(request);
    log.info("result: {}", result);
  }

  @Test
  public void testQueryBusiFavorCouponsDisassociate() throws WxPayException {
    BusiFavorCouponsAssociateRequest request = new BusiFavorCouponsAssociateRequest();
    request.setStockId("100088");
    request.setCouponCode("sxxe34343434");
    request.setOutTradeNo("MCH_102233445");
    request.setOutRequestNo("1002600620019090123143254435");
    BusiFavorCouponsAssociateResult result = wxPayService.getMarketingBusiFavorService().queryBusiFavorCouponsDisAssociate(request);
    log.info("result: {}", result);
  }

  @Test
  public void testUpdateBusiFavorStocksBudget() throws WxPayException {
    BusiFavorStocksBudgetRequest request = new BusiFavorStocksBudgetRequest();
    request.setTargetMaxCoupons(10);
    request.setCurrentMaxCoupons(4);
    request.setModifyBudgetRequestNo("1002600620019090123143254436");
    BusiFavorStocksBudgetResult result = wxPayService.getMarketingBusiFavorService().updateBusiFavorStocksBudget("98065001", request);
    log.info("result: {}", result);
  }

  @Test
  public void testUpdateFavorStocksV3() throws WxPayException {
    BusiFavorStocksCreateRequest request = new BusiFavorStocksCreateRequest();
    request.setStockName("买价值984元3大罐送价值316元2小罐1");
    request.setComment("买价值984元3大罐送价值316元2小罐");
    request.setGoodsName("仅供安满品牌商品使用");
    request.setOutRequestNo(wxPayService.getConfig().getMchId() + "20210204" + "1234567890");


//    //核销规则
//    CouponUseRule couponUseRule = new CouponUseRule();

//    //线下核销
//    couponUseRule.setUseMethod("OFF_LINE");
//
//    //券可核销时间
//    CouponAvailableTime couponAvailableTime = new CouponAvailableTime();
//    couponAvailableTime.setAvailableBeginTime("2021-05-20T13:29:35+08:00");
//    couponAvailableTime.setAvailableEndTime("2021-05-21T13:29:35+08:00");
//    couponUseRule.setCouponAvailableTime(couponAvailableTime);
//
//    //固定面额满减券
//    request.setStockType(StockTypeEnum.NORMAL);
//    FixedNormalCoupon fixedNormalCoupon = new FixedNormalCoupon();
//    fixedNormalCoupon.setDiscountAmount(31600);
//    fixedNormalCoupon.setTransactionMinimum(98400);
//    couponUseRule.setFixedNormalCoupon(fixedNormalCoupon);
//    request.setCouponUseRule(couponUseRule);
//
//    //发放规则
//    StockSendRule stockSendRule = new StockSendRule();
//    stockSendRule.setMaxCoupons(100);
//    stockSendRule.setMaxCouponsPerUser(5);
//    request.setStockSendRule(stockSendRule);

    String result = wxPayService.getMarketingBusiFavorService().updateBusiFavorStocksV3("1252430000000012", request);

    log.info("result: [{}]", result);
  }

  @Test
  public void testReturnBusiFavorCoupons() throws WxPayException {
    BusiFavorCouponsReturnRequest request = new BusiFavorCouponsReturnRequest();
    request.setReturnRequestNo("1002600620019090123143254436");
    request.setStockId("1234567891");
    request.setCouponCode("sxxe34343434");
    BusiFavorCouponsReturnResult result = wxPayService.getMarketingBusiFavorService().returnBusiFavorCoupons(request);
    log.info("result: {}", result);
  }

  @Test
  public void testDeactivateBusiFavorCoupons() throws WxPayException {
    BusiFavorCouponsDeactivateRequest request = new BusiFavorCouponsDeactivateRequest();
    request.setDeactivateRequestNo("1002600620019090123143254436");
    request.setDeactivateReason("此券使用时间设置错误");
    request.setStockId("1234567891");
    request.setCouponCode("sxxe34343434");
    BusiFavorCouponsDeactivateResult result = wxPayService.getMarketingBusiFavorService().deactiveBusiFavorCoupons(request);
    log.info("result: {}", result);
  }

  @Test
  public void testSubsidyBusiFavorPayReceipts() throws WxPayException {
    BusiFavorSubsidyRequest request = new BusiFavorSubsidyRequest();
    request.setStockId("128888000000001");
    request.setCouponCode("ABCD12345678");
    request.setTransactionId("4200000913202101152566792388");
    request.setPayeeMerchant("1466573302");
    request.setPayerMerchant("1466573302");
    request.setAmount(100);
    request.setDescription("20210115DESCRIPTION");
    request.setOutSubsidyNo("subsidy-abcd-12345678");
    BusiFavorSubsidyResult result = wxPayService.getMarketingBusiFavorService().subsidyBusiFavorPayReceipts(request);

    log.info("result: {}", result);
  }

  @Test
  public void testQueryBusiFavorSubsidyPayReceipts() throws WxPayException {
    BusiFavorSubsidyResult result = wxPayService.getMarketingBusiFavorService().queryBusiFavorSubsidyPayReceipts("1120200119165100000000000001");
    log.info("result: {}", result);
  }

  @Test
  public void testNotifyBusiFavor() throws WxPayException {
    BusiFavorNotifyRequest request = new BusiFavorNotifyRequest();
    request.setId("8b33f79f-8869-5ae5-b41b-3c0b59f957d0");
    request.setCreateTime("2019-12-12T16:54:38+08:00");
    request.setEventType("COUPON.SEND");
    request.setResourceType("encrypt-resource");

    BusiFavorNotifyResult result = wxPayService.getMarketingBusiFavorService().notifyBusiFavor("https://www.yujam.com", request);

    log.info("result: {}", result);
  }
}
