package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * 企业微信红包返回
 *
 * @author wuyong
 * @date 2019-12-01 11:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class EntPayRedpackResult extends BaseWxPayResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 商户订单号
   * 商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字
   */
  @XStreamAlias("mch_billno")
  private String mchBillNo;

  /**
   * 商户号
   * 微信支付分配的商户号
   */
  @XStreamAlias("mch_id")
  private String mchId;

  /**
   * 公众账号appid
   * 商户appid，接口传入的所有appid应该为公众号的appid，不能为APP的appid
   */
  @XStreamAlias("wxappid")
  private String wxAppId;

  /**
   * 用户openid
   * 接受收红包的用户在wxappid下的openid
   */
  @XStreamAlias("re_openid")
  private String reOpenid;

  /**
   * 付款金额
   * 付款金额，单位分
   */
  @XStreamAlias("total_amount")
  private String totalAmount;

  /**
   * 微信单号
   * 红包订单的微信单号
   */
  @XStreamAlias("send_listid")
  private String sendListId;

  /**
   * 发送者名称
   * 红包发送者名称(需要utf-8格式)
   */
  @XStreamAlias("sender_name")
  private String senderName;

  /**
   * 发送者头像
   * 发送者头像素材id，通过企业微信开放上传素材接口获取
   */
  @XStreamAlias("sender_header_media_id")
  private String senderHeaderMediaId;

  @Override
  protected void loadXml(Document d) {
    mchBillNo = readXmlString(d, "mch_billno");
    mchId = readXmlString(d, "mch_id");
    wxAppId = readXmlString(d, "wxappid");
    reOpenid = readXmlString(d, "re_openid");
    totalAmount = readXmlString(d, "total_amount");
    sendListId = readXmlString(d, "send_listid");
    senderName = readXmlString(d, "sender_name");
    senderHeaderMediaId = readXmlString(d, "sender_header_media_id");
  }
}
