package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.applyment.ModifySettlementRequest;
import com.github.binarywang.wxpay.bean.applyment.WxPayApplyment4SubCreateRequest;
import com.github.binarywang.wxpay.bean.applyment.WxPayApplymentCreateResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.Applyment4SubService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class Applyment4SubServiceImplTest {
  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void testCreateApply() throws WxPayException {
    Applyment4SubService applyment4SubService=new Applyment4SubServiceImpl(wxPayService);
    String requestParamStr="{}";
    /*
    {"business_code":"1596785690732","contact_info":{"contact_name":"张三","contact_id_number":"110110202001011234","mobile_phone":"13112345678","contact_email":"abc@qq.com"},"subject_info":{"subject_type":"SUBJECT_TYPE_ENTERPRISE","business_license_info":{"license_copy":"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI","license_number":"123456789012345678","merchant_name":"腾讯科技有限公司","legal_person":"张三"},"identity_info":{"id_doc_type":"IDENTIFICATION_TYPE_IDCARD","id_card_info":{"id_card_copy":"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI","id_card_national":"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI","id_card_name":"张三","id_card_number":"110110202001011234","card_period_begin":"2016-06-06","card_period_end":"2026-06-06"},"owner":false},"ubo_info":{"id_type":"IDENTIFICATION_TYPE_IDCARD","id_card_copy":"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI","id_card_national":"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI","id_doc_copy":"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI","name":"张三","id_number":"110110202001011234","id_period_begin":"2016-06-06","id_period_end":"2026-06-06"}},"business_info":{"merchant_shortname":"商户简称","service_phone":"13212345678","sales_info":{"sales_scenes_type":["SALES_SCENES_MINI_PROGRAM"],"mini_program_info":{"mini_program_appid":"wxe5f52902cf4de896"}}},"settlement_info":{"settlement_id":"716","qualification_type":"餐饮"}}
    */
    requestParamStr="{\"business_code\":\"1596785690732\",\"contact_info\":{\"contact_name\":\"张三\",\"contact_id_number\":\"110110202001011234\",\"mobile_phone\":\"13112345678\",\"contact_email\":\"abc@qq.com\"},\"subject_info\":{\"subject_type\":\"SUBJECT_TYPE_ENTERPRISE\",\"business_license_info\":{\"license_copy\":\"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI\",\"license_number\":\"123456789012345678\",\"merchant_name\":\"腾讯科技有限公司\",\"legal_person\":\"张三\"},\"identity_info\":{\"id_doc_type\":\"IDENTIFICATION_TYPE_IDCARD\",\"id_card_info\":{\"id_card_copy\":\"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI\",\"id_card_national\":\"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI\",\"id_card_name\":\"张三\",\"id_card_number\":\"110110202001011234\",\"card_period_begin\":\"2016-06-06\",\"card_period_end\":\"2026-06-06\"},\"owner\":false},\"ubo_info\":{\"id_type\":\"IDENTIFICATION_TYPE_IDCARD\",\"id_card_copy\":\"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI\",\"id_card_national\":\"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI\",\"id_doc_copy\":\"mxX07DyfM-bJyGJYCTyW-4wrXpJ5fq_bgYfWkIZZgjenf6Ct1gKV_FpkzgyQrf5ETVEyOWhC_0cbhOATODuLBAkxGl6Cvj31lh6OFAIHnwI\",\"name\":\"张三\",\"id_number\":\"110110202001011234\",\"id_period_begin\":\"2016-06-06\",\"id_period_end\":\"2026-06-06\"}},\"business_info\":{\"merchant_shortname\":\"商户简称\",\"service_phone\":\"13212345678\",\"sales_info\":{\"sales_scenes_type\":[\"SALES_SCENES_MINI_PROGRAM\"],\"mini_program_info\":{\"mini_program_appid\":\"wxe5f52902cf4de896\"}}},\"settlement_info\":{\"settlement_id\":\"716\",\"qualification_type\":\"餐饮\"}}";

    WxPayApplyment4SubCreateRequest request=GSON.fromJson(requestParamStr,WxPayApplyment4SubCreateRequest.class);
    String businessCode = String.valueOf(System.currentTimeMillis());
    request.setBusinessCode(businessCode);

    WxPayApplymentCreateResult apply = applyment4SubService.createApply(request);
    String applymentId = apply.getApplymentId();
    log.info("businessCode:[{}],applymentId:[{}]",businessCode,applymentId);

  }

  @Test
  public void testQueryApplyStatusByBusinessCode() throws WxPayException {
    Applyment4SubService applyment4SubService=new Applyment4SubServiceImpl(wxPayService);
    String businessCode="businessCode";

    applyment4SubService.queryApplyStatusByBusinessCode(businessCode);


  }

  @Test
  public void testQueryApplyStatusByApplymentId() throws WxPayException {
    Applyment4SubService applyment4SubService=new Applyment4SubServiceImpl(wxPayService);
    String applymentId="applymentId";

    applyment4SubService.queryApplyStatusByApplymentId(applymentId);

  }

  @Test
  public void testQuerySettlementBySubMchid() throws WxPayException {
    Applyment4SubService applyment4SubService=new Applyment4SubServiceImpl(wxPayService);
    String subMchid="subMchid";

    applyment4SubService.querySettlementBySubMchid(subMchid);

  }

  @Test
  public void testModifySettlement() throws WxPayException {
    Applyment4SubService applyment4SubService=new Applyment4SubServiceImpl(wxPayService);
    String subMchid="subMchid";
    ModifySettlementRequest modifySettlementRequest = new ModifySettlementRequest();

    applyment4SubService.modifySettlement(subMchid,modifySettlementRequest);
  }

  @Test
  public void testSettlementApplication() throws WxPayException{
    Applyment4SubService applyment4SubService=new Applyment4SubServiceImpl(wxPayService);
    String subMchid="subMchid";
    String applymentId="applymentId";

    applyment4SubService.settlementApplication(subMchid, applymentId);
  }



}
