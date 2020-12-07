package com.github.binarywang.wxpay.bean.entwxpay;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * Created on 2020/11/29.
 * 向员工付款请求对象
 * @author 拎小壶冲
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class EntWxEmpPayRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 字段名：商户订单号.
   * 变量名：partner_trade_no
   * 是否必填：是
   * 示例值：10000098201411111234567890
   * 类型：String
   * 描述：商户订单号
   * </pre>
   */
  @Required
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  /**
   * <pre>
   * 字段名：需保持唯一性 用户openid.
   * 变量名：openid
   * 是否必填：是
   * 示例值：oxTWIuGaIt6gTKsQRLau2M0yL16E
   * 类型：String
   * 描述：商户appid下，某用户的openid
   * </pre>
   */
  @Required
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：设备号.
   * 变量名：device_info
   * 是否必填：否
   * 示例值：13467007045764
   * 类型：String(32)
   * 描述：微信支付分配的终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 字段名：校验用户姓名选项.
   * 变量名：check_name
   * 是否必填：是
   * 示例值：OPTION_CHECK
   * 类型：String
   * 描述：NO_CHECK：不校验真实姓名 
   * FORCE_CHECK：强校验真实姓名（未实名认证的用户会校验失败，无法转账） 
   * OPTION_CHECK：针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
   * </pre>
   */
  @Required
  @XStreamAlias("check_name")
  private String checkName;

  /**
   * <pre>
   * 字段名：收款用户姓名.
   * 变量名：re_user_name
   * 是否必填：可选
   * 示例值：马花花
   * 类型：String
   * 描述：收款用户真实姓名。
   * 如果check_name设置为FORCE_CHECK或OPTION_CHECK，  则必填用户真实姓名
   * </pre>
   */
  @XStreamAlias("re_user_name")
  private String reUserName;

  /**
   * <pre>
   * 字段名：金额.
   * 变量名：amount
   * 是否必填：是
   * 示例值：10099
   * 类型：int
   * 描述：企业付款金额， 单位为分
   * </pre>
   */
  @Required
  @XStreamAlias("amount")
  private Integer amount;

  /**
   * <pre>
   * 字段名：企业付款描述信息.
   * 变量名：desc
   * 是否必填：是
   * 示例值：理赔
   * 类型：String
   * 描述：企业付款操作说明信息。必填。
   * </pre>
   */
  @Required
  @XStreamAlias("desc")
  private String description;

  /**
   * <pre>
   * 字段名：Ip地址.
   * 变量名：spbill_create_ip
   * 是否必填：是
   * 示例值：192.168.0.1
   * 类型：String(32)
   * 描述：调用接口的机器Ip地址
   * </pre>
   */
  @Required
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

  /**
   * <pre>
   *   字段名: 付款消息类型
   *   变量名: ww_msg_type
   *   是否必填： 是
   *   示例值：NORMAL_MSG
   *   描述：NORMAL_MSG：普通付款消息 APPROVAL _MSG：审批付款消息
   * </pre>
   */
  @Required
  @XStreamAlias("ww_msg_type")
  private String wwMsgType;

  /**
   * <pre>
   *   字段名： 审批单号
   *   变量名： approval_number
   *   是否必填： 否
   *   示例值： 201705160008
   *   描述：ww_msg_type为APPROVAL _MSG时，需要填写approval_number
   * </pre>
   */
  @XStreamAlias("approval_number")
  private String approvalNumber;

  /**
   * <pre>
   *   字段名： 审批类型
   *   变量名： approval_type
   *   是否必填： 否
   *   示例值： 1
   *   描述：ww_msg_type为APPROVAL _MSG时，需要填写1
   * </pre>
   */
  @XStreamAlias("approval_type")
  private Integer approvalType;


  /**
   * <pre>
   *   字段名： 项目名称
   *   变量名： act_name
   *   是否必填： 是
   *   示例值： 产品部门报销
   *   描述：项目名称，最长50个utf8字符
   * </pre>
   */
  @Required
  @XStreamAlias("act_name")
  private String actName;

  /**
   * <pre>
   *   字段名： 付款的应用id
   *   变量名： agentid
   *   是否必填： 否
   *   示例值： 1
   *   描述：以企业应用的名义付款，企业应用id，整型，可在企业微信管理端应用的设置页面查看。
   * </pre>
   */
  @XStreamAlias("agentid")
  private Integer agentId;


  @Override
  protected void checkConstraints() throws WxPayException {

  }

  @Override
  protected boolean isWxWorkSign() {
    return true;
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("appid", appid);
    map.put("mch_id", mchId);
    map.put("device_info", deviceInfo);
    map.put("partner_trade_no", partnerTradeNo);
    map.put("openid", openid);
    map.put("check_name", checkName);
    map.put("re_user_name", reUserName);
    map.put("amount", amount.toString());
    map.put("desc", description);
    map.put("spbill_create_ip", spbillCreateIp);
    map.put("act_name", actName);
    map.put("ww_msg_type", wwMsgType);
    map.put("approval_number", approvalNumber);
    map.put("approval_type", approvalType.toString());
    map.put("agentid", agentId.toString());
  }
}
