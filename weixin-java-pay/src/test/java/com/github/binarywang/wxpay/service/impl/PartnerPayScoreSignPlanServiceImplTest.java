package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreSignPlanRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PartnerPayScoreSignPlanService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @className PartnerPayScoreSignPlanServiceImplTest
 * @description
 * @author
 * @createTime 2023/11/6 10:30
 **/
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class PartnerPayScoreSignPlanServiceImplTest {
  @Inject
  private WxPayService wxPayService;

  private static final Gson GSON = new GsonBuilder().create();

  @Test
  public void testcreatePlans()throws WxPayException{
    PartnerPayScoreSignPlanService scoreSignPlan=new PartnerPayScoreSignPlanServiceImpl(wxPayService);
    WxPartnerPayScoreSignPlanRequest request=new WxPartnerPayScoreSignPlanRequest();
    request.setSubMchid("子商户号");
    scoreSignPlan.createPlans(request);
  }

  @Test
  public void testqueryPlans()throws WxPayException{
    PartnerPayScoreSignPlanService scoreSignPlan=new PartnerPayScoreSignPlanServiceImpl(wxPayService);
    scoreSignPlan.queryPlans("merchantPlanNo","子商户号");
  }

  @Test
  public void teststopPlans()throws WxPayException{
    PartnerPayScoreSignPlanService scoreSignPlan=new PartnerPayScoreSignPlanServiceImpl(wxPayService);
    scoreSignPlan.stopPlans("merchantPlanNo","子商户号");
  }

  @Test
  public void testsignPlanServiceOrder()throws WxPayException{
    PartnerPayScoreSignPlanService scoreSignPlan=new PartnerPayScoreSignPlanServiceImpl(wxPayService);
    WxPartnerPayScoreSignPlanRequest request=new WxPartnerPayScoreSignPlanRequest();

    scoreSignPlan.signPlanServiceOrder(request);
  }

  @Test
  public void testcreateUserSignPlans()throws WxPayException{
    PartnerPayScoreSignPlanService scoreSignPlan=new PartnerPayScoreSignPlanServiceImpl(wxPayService);
    WxPartnerPayScoreSignPlanRequest request=new WxPartnerPayScoreSignPlanRequest();
    scoreSignPlan.createUserSignPlans(request);
  }

  @Test
  public void testqueryUserSignPlans()throws WxPayException{
    PartnerPayScoreSignPlanService scoreSignPlan=new PartnerPayScoreSignPlanServiceImpl(wxPayService);
    scoreSignPlan.queryUserSignPlans("merchantPlanNo","子商户号");
  }

  @Test
  public void teststopUserSignPlans()throws WxPayException{
    PartnerPayScoreSignPlanService scoreSignPlan=new PartnerPayScoreSignPlanServiceImpl(wxPayService);
    scoreSignPlan.stopUserSignPlans("merchantPlanNo","子商户号","测试取消");
  }
}
