package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.brandmerchanttransfer.request.*;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandBatchesQueryResult;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandDetailsQueryResult;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandTransferBatchesResult;
import com.github.binarywang.wxpay.bean.merchanttransfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 品牌红包商家转账到零钱（直连商户）
 * @author moran
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class BrandMerchantTransferServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void createBrandTransfer() throws WxPayException {
    String requestParamStr = "{\"batch_name\":\"双十一营销用品牌红包\",\"batch_remark\":\"双十一营销用品牌红包\",\"brand_appid\":\"wxf636efh567hg4356\",\"brand_id\":1234,\"detail_list\":[{\"amount\":100,\"openid\":\"o-MYE42l80oelYMDE34nYD456Xoy\",\"out_detail_no\":\"x23zy545Bd5436\",\"remark\":\"来自XX的红包\",\"user_name\":\"757b340b45ebef5467rter35gf464344v3542sdf4t6re4tb4f54ty45t4yyry45\"}],\"out_batch_no\":\"plfk2020042013\",\"scene\":\"CUSTOM_SEND\",\"template_id\":\"123400001\",\"total_amount\":10000,\"total_num\":10}";

    BrandTransferBatchesRequest request = GSON.fromJson(requestParamStr, BrandTransferBatchesRequest.class);
    BrandTransferBatchesResult result = wxPayService.getBrandMerchantTransferService().createBrandTransfer(request);
    log.info(result.toString());
  }

  @Test
  public void queryBrandWxBatches() throws WxPayException {
    String requestParamStr = "{\"batch_no\":\"1030000071100999991182020050700019480001\",\"need_query_detail\":true,\"detail_status\":\"DETAIL_VIEW_FAIL\"}";

    BrandWxBatchesQueryRequest request = GSON.fromJson(requestParamStr, BrandWxBatchesQueryRequest.class);
    log.info("request:{}",request);
    BrandBatchesQueryResult result = wxPayService.getBrandMerchantTransferService().queryBrandWxBatches(request);
    log.info(result.toString());
  }

  @Test
  public void queryBrandWxDetails() throws WxPayException {
    String requestParamStr = "{\"batch_no\":\"1030000071100999991182020050700019480001\",\"detail_no\":\"1040000071100999991182020050700019500100\"}";

    BrandWxDetailsQueryRequest request = GSON.fromJson(requestParamStr, BrandWxDetailsQueryRequest.class);
    BrandDetailsQueryResult result = wxPayService.getBrandMerchantTransferService().queryBrandWxDetails(request);
    log.info(result.toString());
  }

  @Test
  public void queryBrandMerchantBatches() throws WxPayException {
    String requestParamStr = "{\"out_batch_no\":\"plfk2020042013\",\"need_query_detail\":true,\"detail_status\":\"DETAIL_VIEW_FAIL\"}";

    BrandMerchantBatchesQueryRequest request = GSON.fromJson(requestParamStr, BrandMerchantBatchesQueryRequest.class);
    BrandBatchesQueryResult result = wxPayService.getBrandMerchantTransferService().queryBrandMerchantBatches(request);
    log.info(result.toString());
  }

  @Test
  public void queryBrandMerchantDetails() throws WxPayException {
    String requestParamStr = "{\"out_batch_no\":\"plfk2020042013\",\"out_detail_no\":\"x23zy545Bd5436\"}";

    BrandMerchantDetailsQueryRequest request = GSON.fromJson(requestParamStr, BrandMerchantDetailsQueryRequest.class);
    BrandDetailsQueryResult result = wxPayService.getBrandMerchantTransferService().queryBrandMerchantDetails(request);
    log.info(result.toString());
  }

}
