package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.complaint.*;
import com.github.binarywang.wxpay.bean.profitsharing.*;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.crypto.BadPaddingException;

/**
 * <pre>
 *  消费者投诉2.0 测试类
 * </pre>
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class ComplaintServiceImplTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  private static final String complaintId = "200231020220320120496109901";

  /**
   * 查询投诉单列表API
   * @throws WxPayException
   */
  @Test
  public void testQueryComplaints() throws WxPayException, BadPaddingException {
    ComplaintRequest request = ComplaintRequest
      .newBuilder()
      .offset(0)
      .limit(10)
      .beginDate("2022-03-01")
      .endDate("2022-03-20")
      .complaintedMchid(this.payService.getConfig().getMchId())
      .build();
    this.logger.info(this.payService.getComplaintsService().queryComplaints(request).toString());
  }

  /**
   * 查询投诉单详情API
   * @throws WxPayException
   */
  @Test
  public void testGetComplaint() throws WxPayException, BadPaddingException {
    ComplaintDetailRequest request = ComplaintDetailRequest
      .newBuilder()
      .complaintId(complaintId)
      .build();
    this.logger.info(this.payService.getComplaintsService().getComplaint(request).toString());
  }

  /**
   * 查询投诉协商历史API
   * @throws WxPayException
   */
  @Test
  public void testQueryNegotiationHistorys() throws WxPayException {
    NegotiationHistoryRequest request = NegotiationHistoryRequest
      .newBuilder()
      .complaintId(complaintId)
      .offset(0)
      .limit(20)
      .build();
    this.logger.info(this.payService.getComplaintsService().queryNegotiationHistorys(request).toString());
  }

  /**
   * 创建投诉通知回调地址API
   * @throws WxPayException
   */
  @Test
  public void testAddComplaintNotifyUrl() throws WxPayException {
    ComplaintNotifyUrlRequest request = ComplaintNotifyUrlRequest
      .newBuilder()
      .url("https://jeepay.natapp4.cc")
      .build();
    this.logger.info(this.payService.getComplaintsService().addComplaintNotifyUrl(request).toString());
  }

  /**
   * 查询投诉通知回调地址API
   * @throws WxPayException
   */
  @Test
  public void testGetComplaintNotifyUrl() throws WxPayException {
    this.logger.info(this.payService.getComplaintsService().getComplaintNotifyUrl().toString());
  }

  /**
   * 更新投诉通知回调地址API
   * @throws WxPayException
   */
  @Test
  public void testUpdateComplaintNotifyUrl() throws WxPayException {
    ComplaintNotifyUrlRequest request = ComplaintNotifyUrlRequest
      .newBuilder()
      .url("https://jeepay1.natapp4.cc")
      .build();
    this.logger.info(this.payService.getComplaintsService().updateComplaintNotifyUrl(request).toString());
  }

  /**
   * 删除投诉通知回调地址API
   * @throws WxPayException
   */
  @Test
  public void testDeleteComplaintNotifyUrl() throws WxPayException {
    this.payService.getComplaintsService().deleteComplaintNotifyUrl();
  }

  /**
   * 提交回复API
   * @throws WxPayException
   */
  @Test
  public void testSubmitResponse() throws WxPayException {
    ResponseRequest request = ResponseRequest
      .newBuilder()
      .complaintId(complaintId)
      .complaintedMchid(this.payService.getConfig().getMchId())
      .responseContent("测试投诉接口1233，正在处理，不要炸鸡")
      //.jumpUrl("https://www.baidu.com")
      //.jumpUrlText("问题解决方案")
      .build();
    this.payService.getComplaintsService().submitResponse(request);
  }

  /**
   * 反馈处理完成API
   * @throws WxPayException
   */
  @Test
  public void testComplete() throws WxPayException {
    CompleteRequest request = CompleteRequest
      .newBuilder()
      .complaintId(complaintId)
      .complaintedMchid(this.payService.getConfig().getMchId())
      .build();
    this.payService.getComplaintsService().complete(request);
  }

}
