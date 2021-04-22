package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * 红包发送记录查询返回
 *
 * @author wuyong
 * @date 2019-12-01 17:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class EntPayRedpackQueryResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 3127509905347445197L;

  /**
   * 商户订单号
   * 商户使用查询API填写的商户单号的原路返回
   */
  @XStreamAlias("mch_billno")
  protected String mchBillNo;

  /**
   * 红包单号
   * 使用API发放现金红包时返回的红包单号
   */
  @XStreamAlias("detailId")
  private String detailId;
  /**
   * 红包状态
   * SENDING:发放
   * SENT:
   * 已发放待领取
   * FAILED：发放失败
   * RECEIVED:已领取
   * RFUND_ING:退款中 REFUND:已退款
   */
  @XStreamAlias("status")
  private String status;

  /**
   * 发放类型
   * API:通过API接口发放
   */
  @XStreamAlias("send_type")
  private String sendType;

  /**
   * 红包金额
   * 红包总金额（单位分）
   */
  @XStreamAlias("total_amount")
  private Integer totalAmount;

  /**
   * 失败原因
   * 发送失败原因
   */
  @XStreamAlias("reason")
  private Integer reason;

  /**
   * 红包发送时间
   */
  @XStreamAlias("send_time")
  private String sendTime;
  /**
   * 红包的退款时间
   */
  @XStreamAlias("refund_time")
  private String refundTime;

  /**
   * 红包退款金额
   */
  @XStreamAlias("refund_amount")
  private Integer refundAmount;

  /**
   * 祝福语
   */
  @XStreamAlias("wishing")
  private String wishing;

  /**
   * 备注
   */
  @XStreamAlias("remark")
  private String remark;

  /**
   * 活动名称
   */
  @XStreamAlias("act_name")
  private String actName;

  /**
   * 领取红包的Openid
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * 金额
   */
  @XStreamAlias("amount")
  private Integer amount;

  /**
   * 接收时间
   */
  @XStreamAlias("rcv_time")
  private Integer rcvTime;

  /**
   * 发送者名称
   */
  @XStreamAlias("sender_name")
  private Integer senderName;

  /**
   * 发送者头像
   * 通过企业微信开放接口上传获取
   */
  @XStreamAlias("sender_header_media_id")
  private Integer senderHeaderMediaId;

  @Override
  protected void loadXml(Document d) {
    mchBillNo = readXmlString(d, "mch_billno");
    detailId = readXmlString(d, "detailId");
    status = readXmlString(d, "status");
    sendType = readXmlString(d, "send_type");
    totalAmount = readXmlInteger(d, "total_amount");
    reason = readXmlInteger(d, "reason");
    sendTime = readXmlString(d, "send_time");
    refundTime = readXmlString(d, "refund_time");
    refundAmount = readXmlInteger(d, "refund_amount");
    wishing = readXmlString(d, "wishing");
    remark = readXmlString(d, "remark");
    actName = readXmlString(d, "act_name");
    openid = readXmlString(d, "openid");
    amount = readXmlInteger(d, "amount");
    rcvTime = readXmlInteger(d, "rcv_time");
    senderName = readXmlInteger(d, "sender_name");
    senderHeaderMediaId = readXmlInteger(d, "sender_header_media_id");
  }
}
