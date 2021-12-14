package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.FundBalanceResult;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.marketing.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.crypto.BadPaddingException;
import java.io.InputStream;

/**
 * 批量转账到零钱（服务商）
 *
 * @author xiaoqiang
 * @date 2021/12/9
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class PartnerTransferServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void batchTransfer() throws WxPayException {
    String requestParamStr = "{\"sub_mchid\":\"1608*****1\",\"authorization_type\":\"FUND_AUTHORIZATION_TYPE\",\"out_batch_no\":\"202108241345*****4618387\",\"batch_name\":\"用户提现批次单2021-12-13_13_45_50\",\"batch_remark\":\"用户>提现\",\"total_amount\":30,\"total_num\":1,\"transfer_detail_list\":[{\"out_detail_no\":\"DN202112131345506240631640467671\",\"transfer_amount\":30,\"transfer_remark\":\"钱包提现\",\"openid\":\"oUaIE6PdSuBpsHAOtvf_jsgtqu5I\",\"user_name\":\"Q2FoOMuf1Ulsab+j0nObLjkIZAUZan8Z7RaEU5qOjv1RUq5ImuqqAqoQZ4f/zD5CMxuLD7lM1TIdGIvrvO8pe2YOwoUdRxiRzDX+Z0Rsy5Y9QqEiuHHK1JTR7vC18eKp0a4PlY7K4jUl49jG0QE+6gOG83Cqj3Z9dupPor94fPRUM/ZIzF293ONgSJW1iuHkd6g7EHTpizHZ/r5XcT+qh*************************kqjtVkT3GiuDXmMA8d/hO85uY50ItNNa5Ov8kmJbLCgFreoS49LUEwj/yuDap6F4g\\u003d\\u003d\"}],\"sp_appid\":\"wx6aa************ef\",\"transfer_purpose\":\"OTHERS\"}";

    PartnerTransferRequest request = GSON.fromJson(requestParamStr, PartnerTransferRequest.class);

    PartnerTransferResult partnerTransferResult = wxPayService.getPartnerTransferService().batchTransfer(request);
    log.info(partnerTransferResult.toString());
  }


  @Test
  public void queryBatchByBatchId() throws WxPayException {
    BatchNumberRequest request = new BatchNumberRequest();
    request.setBatchId("1030000071100999991182020050700019480001");
    request.setNeedQueryDetail(true);
    request.setDetailStatus("ALL");
    BatchNumberResult batchResult = wxPayService.getPartnerTransferService().queryBatchByBatchId(request);
    log.info(batchResult.toString());
  }


  @Test
  public void queryBatchDetailByWeChat() throws WxPayException, BadPaddingException {
    String batchId = "1030000071100999991182020050700019480001";
    String detailId = "1040000071100999991182020050700019500100";
    BatchDetailsResult batchResult = wxPayService.getPartnerTransferService().queryBatchDetailByWeChat(batchId, detailId);
    log.info(batchResult.toString());
  }

  @Test
  public void queryBatchByOutBatchNo() throws WxPayException {
    MerchantBatchRequest request = new MerchantBatchRequest();
    request.setOutBatchNo("10300000************0019480001");
    request.setDetailStatus("ALL");
    request.setNeedQueryDetail(true);
    BatchNumberResult batchResult = wxPayService.getPartnerTransferService().queryBatchByOutBatchNo(request);
    log.info(batchResult.toString());
  }

  @Test
  public void queryBatchDetailByMch() throws WxPayException, BadPaddingException {
    String outBatchNo = "10300000************0019480001";
    String outDetailNo = "10***********0019480001";
    BatchDetailsResult batchResult = wxPayService.getPartnerTransferService().queryBatchDetailByMch(outBatchNo, outDetailNo);
    log.info(batchResult.toString());
  }

  @Test
  public void receiptBill() throws WxPayException {
    ReceiptBillRequest request = new ReceiptBillRequest();
    request.setOutBatchNo("10300000************0019480001");
    BillReceiptResult batchResult = wxPayService.getPartnerTransferService().receiptBill(request);
    log.info(batchResult.toString());
  }

  @Test
  public void queryBillReceipt() throws WxPayException {
    String outBatchNo = "10300000************0019480001";
    BillReceiptResult batchResult = wxPayService.getPartnerTransferService().queryBillReceipt(outBatchNo);
    log.info(batchResult.toString());
  }

  @Test
  public void transferElectronic() throws WxPayException {
    ElectronicReceiptsRequest request = new ElectronicReceiptsRequest();
    request.setAcceptType("BATCH_TRANSFER");
    request.setOutBatchNo("GD2021011610162610BBdkkIwcu3");
    request.setOutDetailNo("mx0911231610162610v4CNkO4HAf");
    ElectronicReceiptsResult batchResult = wxPayService.getPartnerTransferService().transferElectronic(request);
    log.info(batchResult.toString());
  }

  @Test
  public void queryTransferElectronicResult() throws WxPayException {
    ElectronicReceiptsRequest request = new ElectronicReceiptsRequest();
    request.setAcceptType("BATCH_TRANSFER");
    request.setOutBatchNo("GD2021011610162610BBdkkIwcu3");
    request.setOutDetailNo("mx0911231610162610v4CNkO4HAf");
    ElectronicReceiptsResult batchResult = wxPayService.getPartnerTransferService().queryTransferElectronicResult(request);
    log.info(batchResult.toString());
  }


  @Test
  public void transferDownload() throws WxPayException {
    String url = "https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx";
    InputStream batchResult = wxPayService.getPartnerTransferService().transferDownload(url);
    log.info(batchResult.toString());
  }

  @Test
  public void fundBalance() throws WxPayException {
    FundBalanceResult batchResult = wxPayService.getPartnerTransferService().fundBalance(SpAccountTypeEnum.BASIC);
    log.info(batchResult.toString());
  }

  @Test
  public void spDayEndBalance() {
    String date = "2020-09-11";
    FundBalanceResult batchResult = wxPayService.getPartnerTransferService().spDayEndBalance(SpAccountTypeEnum.BASIC, date);
    log.info(batchResult.toString());
  }

}
