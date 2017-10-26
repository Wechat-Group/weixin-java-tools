package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <pre>
 *   注释中各行对应含义：
 *   字段名
 *   字段
 *   必填
 *   示例值
 *   类型
 *   说明
 * Created by Binary Wang on 2016-11-28.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayRedpackQueryResult extends WxPayBaseResult {

  /**
   * <pre>
   * 商户订单号
   * mch_billno
   * 是
   * 10000098201411111234567890
   * String(28)
   * 商户使用查询API填写的商户单号的原路返回
   * </pre>
   */
  @XStreamAlias("mch_billno")
  private String mchBillNo;

  /**
   * <pre>
   * 红包单号
   * detail_id
   * 是
   * 1000000000201503283103439304
   * String(32)
   * 使用API发放现金红包时返回的红包单号
   * </pre>
   */
  @XStreamAlias("detail_id")
  private String detailId;

  /**
   * <pre>
   * 红包状态
   * status
   * 是
   * RECEIVED
   * string(16)
   * SENDING:发放中，
   * SENT:已发放待领取，
   * FAILED：发放失败，
   * RECEIVED:已领取，
   * RFUND_ING:退款中，
   * REFUND:已退款
   * </pre>
   */
  @XStreamAlias("status")
  private String status;

  /**
   * <pre>
   * 发放类型
   * send_type
   * 是
   * API
   * String(32)
   *  API:通过API接口发放，
   *  UPLOAD:通过上传文件方式发放，
   *  ACTIVITY:通过活动方式发放
   * </pre>
   */
  @XStreamAlias("send_type")
  private String sendType;

  /**
   * <pre>
   * 红包类型
   * hb_type
   * 是
   * GROUP
   * String(32)
   *  GROUP:裂变红包，
   *  NORMAL:普通红包
   * </pre>
   */
  @XStreamAlias("hb_type")
  private String hbType;

  /**
   * <pre>
   * 红包个数
   * total_num
   * 是
   * 1
   * int
   * 红包个数
   * </pre>
   */
  @XStreamAlias("total_num")
  private Integer totalNum;

  /**
   * <pre>
   * 红包金额
   * total_amount
   * 是
   * 5000
   * int
   * 红包总金额（单位分）
   * </pre>
   */
  @XStreamAlias("total_amount")
  private Integer totalAmount;

  /**
   * <pre>
   * 失败原因
   * reason
   * 否
   * 余额不足
   * String(32)
   * 发送失败原因
   * </pre>
   */
  @XStreamAlias("reason")
  private String reason;

  /**
   * <pre>
   * 红包发送时间
   * send_time
   * 是
   * 2015-04-21 20:00:00
   * String(32)
   * 红包的发送时间
   * </pre>
   */
  @XStreamAlias("send_time")
  private String sendTime;

  /**
   * <pre>
   * 红包退款时间
   * refund_time
   * 否
   * 2015-04-21 23:03:00
   * String(32)
   * 红包的退款时间（如果其未领取的退款）
   * </pre>
   */
  @XStreamAlias("refund_time")
  private String refundTime;

  /**
   * <pre>
   * 红包退款金额
   * refund_amount
   * 否
   * 8000
   * Int
   * 红包退款金额
   * </pre>
   */
  @XStreamAlias("refund_amount")
  private Integer refundAmount;

  /**
   * <pre>
   * 祝福语
   * wishing
   * 否
   * 新年快乐
   * String(128)
   * 祝福语
   * </pre>
   */
  @XStreamAlias("wishing")
  private String wishing;

  /**
   * <pre>
   * 活动描述
   * remark
   * 否
   * 新年红包
   * String(256)
   * 活动描述，低版本微信可见
   * </pre>
   */
  @XStreamAlias("remark")
  private String remark;

  /**
   * <pre>
   * 活动名称
   * act_name
   * 否
   * 新年红包
   * String(32)
   * 发红包的活动名称
   * </pre>
   */
  @XStreamAlias("act_name")
  private String actName;

  /**
   * <pre>
   * 裂变红包领取列表
   * hblist
   * 否
   *
   *
   * 裂变红包的领取列表
   * </pre>
   */
  @XStreamAlias("hblist")
  private String hblist;

  /**
   * <pre>
   * 领取红包的Openid
   * openid
   * 是
   * ohO4GtzOAAYMp2yapORH3dQB3W18
   * String(32)
   * 领取红包的openid
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 金额
   * amount
   * 是
   * 100
   * int
   * 领取金额
   * </pre>
   */
  @XStreamAlias("amount")
  private Integer amount;

  /**
   * <pre>
   * 接收时间
   * rcv_time
   * 是
   * 2015-04-21 20:00:00
   * String(32)
   * 领取红包的时间
   * </pre>
   */
  @XStreamAlias("rcv_time")
  private String receiveTime;
}
