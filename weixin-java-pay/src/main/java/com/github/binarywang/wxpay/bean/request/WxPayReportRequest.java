package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 * 微信支付-交易保障请求参数
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayReportRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 2667579776376527663L;

  /**
   * <pre>
   * 设备号.
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 商户自定义的终端设备号，如门店编号、设备的ID等
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 接口URL.
   * interface_url
   * 是
   * String(127)
   * https://api.mch.weixin.qq.com/pay/unifiedorder
   * 报对应的接口的完整URL，类似：https://api.mch.weixin.qq.com/pay/unifiedorder，
   * 对于刷卡支付，为更好的和商户共同分析一次业务行为的整体耗时情况，
   * 对于两种接入模式，请都在门店侧对一次刷卡支付进行一次单独的整体上报，
   * 上报URL指定为：https://api.mch.weixin.qq.com/pay/micropay/total，关于两种接入模式具体可参考本文档章节：
   * 刷卡支付商户接入模式，其它接口调用仍然按照调用一次，上报一次来进行。
   * </pre>
   */
  @Required
  @XStreamAlias("interface_url")
  private String interfaceUrl;
  /**
   * <pre>
   * 接口耗时.
   * execute_time
   * 是
   * Int
   * 1000
   * 接口耗时情况，单位为毫秒
   * </pre>
   */
  @Required
  @XStreamAlias("execute_time_")//估计是官方接口搞错了
  private Integer executeTime;
  /**
   * <pre>
   * 返回状态码.
   * return_code
   * 是
   * String(16)
   * SUCCESS
   * SUCCESS/FAIL，此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
   * </pre>
   */
  @Required
  @XStreamAlias("return_code")
  private String returnCode;
  /**
   * <pre>
   * 返回信息.
   * return_msg
   * 否
   * String(128)
   * 签名失败
   * 返回信息，如非空，为错误原因，签名失败，参数格式校验错误
   * </pre>
   */
  @XStreamAlias("return_msg")
  private String returnMsg;
  /**
   * <pre>
   * 业务结果.
   * result_code
   * 是
   * String(16)
   * SUCCESS
   * SUCCESS/FAIL
   * </pre>
   */
  @Required
  @XStreamAlias("result_code")
  private String resultCode;
  /**
   * <pre>
   * 错误代码.
   * err_code
   * 否
   * String(32)
   * SYSTEMERROR
   * ORDERNOTEXIST—订单不存在，SYSTEMERROR—系统错误
   * </pre>
   */
  @XStreamAlias("err_code")
  private String errCode;
  /**
   * <pre>
   * 错误代码描述.
   * err_code_des
   * 否
   * String(128)
   * 系统错误
   * 结果信息描述
   * </pre>
   */
  @XStreamAlias("err_code_des")
  private String errCodeDes;
  /**
   * <pre>
   * 商户订单号.
   * out_trade_no
   * 否
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的订单号,商户可以在上报时提供相关商户订单号方便微信支付更好的提高服务质量。
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  /**
   * <pre>
   * 访问接口IP.
   * user_ip
   * 是
   * String(16)
   * 8.8.8.8
   * 发起接口调用时的机器IP
   * </pre>
   */
  @Required
  @XStreamAlias("user_ip")
  private String userIp;
  /**
   * <pre>
   * 商户上报时间.
   * time
   * 否
   * String(14)
   * 20091227091010
   * 系统时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
   * </pre>
   */
  @XStreamAlias("time")
  private String time;

  @Override
  protected void checkConstraints() {
    //do nothing
  }
}
