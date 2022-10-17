package com.github.binarywang.wxpay.service.impl;

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
 * 商家转账到零钱（直连商户）
 * @author glz
 * created on  2022/6/11
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class MerchantTransferServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void createTransfer() throws WxPayException {
    String requestParamStr = "{\"out_batch_no\":\"p11lfk2020042013\",\"batch_name\":\"xxx\",\"batch_remark\":\"xxx\",\"total_amount\":30,\"total_num\":1,\"transfer_detail_list\":[{\"out_detail_no\":\"x23zy545Bd5436\",\"transfer_amount\":30,\"transfer_remark\":\"5586提款\",\"openid\":\"or1b65DLMUir7F-_vLwKlutmm3qw\",\"user_name\":\"xxx\"}]}";

    TransferCreateRequest request = GSON.fromJson(requestParamStr, TransferCreateRequest.class);
    TransferCreateResult result = wxPayService.getMerchantTransferService().createTransfer(request);
    log.info(result.toString());
  }

  @Test
  public void queryWxBatches() throws WxPayException {
    String requestParamStr = "{\"batch_id\":\"xxx\",\"need_query_detail\":true,\"offset\":0,\"limit\":20,\"detail_status\":\"ALL\"}";

    WxBatchesQueryRequest request = GSON.fromJson(requestParamStr, WxBatchesQueryRequest.class);
    log.info("request:{}",request);
    BatchesQueryResult result = wxPayService.getMerchantTransferService().queryWxBatches(request);
    log.info(result.toString());
  }

  @Test
  public void queryWxDetails() throws WxPayException {
    String requestParamStr = "{\"batch_id\":\"xxx\",\"detail_id\":\"xxx\"}";

    WxDetailsQueryRequest request = GSON.fromJson(requestParamStr, WxDetailsQueryRequest.class);
    DetailsQueryResult result = wxPayService.getMerchantTransferService().queryWxDetails(request);
    log.info(result.toString());
  }

  @Test
  public void queryMerchantBatches() throws WxPayException {
    String requestParamStr = "{\"out_batch_no\":\"p11lfk2020042013\",\"need_query_detail\":true,\"offset\":0,\"limit\":20,\"detail_status\":\"ALL\"}";

    MerchantBatchesQueryRequest request = GSON.fromJson(requestParamStr, MerchantBatchesQueryRequest.class);
    BatchesQueryResult result = wxPayService.getMerchantTransferService().queryMerchantBatches(request);
    log.info(result.toString());
  }

  @Test
  public void queryMerchantDetails() throws WxPayException {
    String requestParamStr = "{\"out_detail_no\":\"x23zy545Bd5436\",\"out_batch_no\":\"p11lfk2020042013\"}";

    MerchantDetailsQueryRequest request = GSON.fromJson(requestParamStr, MerchantDetailsQueryRequest.class);
    DetailsQueryResult result = wxPayService.getMerchantTransferService().queryMerchantDetails(request);
    log.info(result.toString());
  }

  @Test
  public void applyElectronicBill() throws WxPayException {
    String requestParamStr = "{\"out_batch_no\":\"p11lfk2020042013\"}";

    ElectronicBillApplyRequest request = GSON.fromJson(requestParamStr, ElectronicBillApplyRequest.class);
    ElectronicBillResult result = wxPayService.getMerchantTransferService().applyElectronicBill(request);
    log.info(result.toString());
  }

  @Test
  public void queryElectronicBill() throws WxPayException {
    String outBatchNo = "p11lfk2020042013";

    ElectronicBillResult result = wxPayService.getMerchantTransferService().queryElectronicBill(outBatchNo);
    log.info(result.toString());
  }

  @Test
  public void applyDetailElectronicBill() throws WxPayException {
    String requestParamStr = "{\"accept_type\":\"BATCH_TRANSFER\",\"out_batch_no\":\"p11lfk2020042013\",\"out_detail_no\":\"x23zy545Bd5436\"}";

    DetailElectronicBillRequest request = GSON.fromJson(requestParamStr, DetailElectronicBillRequest.class);
    DetailElectronicBillResult result = wxPayService.getMerchantTransferService().applyDetailElectronicBill(request);
    log.info(result.toString());
  }

  @Test
  public void queryDetailElectronicBill() throws WxPayException {
    String requestParamStr = "{\"accept_type\":\"BATCH_TRANSFER\",\"out_batch_no\":\"p11lfk2020042013\",\"out_detail_no\":\"x23zy545Bd5436\"}";

    DetailElectronicBillRequest request = GSON.fromJson(requestParamStr, DetailElectronicBillRequest.class);
    DetailElectronicBillResult result = wxPayService.getMerchantTransferService().queryDetailElectronicBill(request);
    log.info(result.toString());
  }

}
