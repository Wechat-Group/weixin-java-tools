package me.chanjar.weixin.mp.bean.pay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * <pre>
 * 企业付款请求对象
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * </pre>
 * Created by Binary Wang on 2016/10/02.
 * @author binarywang (https://github.com/binarywang)
 */
@XStreamAlias("xml")
public class WxEntPayRequest extends WxPayBaseRequest{
  /**
   * <pre>
  * 公众账号appid
  * mch_appid
  * 是
  * wx8888888888888888
  * String
  * 微信分配的公众账号ID（企业号corpid即为此appId）
   * </pre>
  */
  @XStreamAlias("mch_appid")
  private String mchAppid;

  /**
   * <pre>
  *  商户号
  *  mchid
  *  是
  *  1900000109
  *  String(32)
  *  微信支付分配的商户号
   * </pre>
  */
  @XStreamAlias("mchid")
  private String mchId;

  /**
   * <pre>
  * 设备号
  * device_info
  * 否
  * 13467007045764
  * String(32)
  *微信支付分配的终端设备号
   * </pre>
  */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
  * 商户订单号
  * partner_trade_no
  * 是
  * 10000098201411111234567890
  * String
  * 商户订单号
   * </pre>
  */
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  /**
   * <pre>
  * 需保持唯一性 用户openid
  * openid
  * 是
  * oxTWIuGaIt6gTKsQRLau2M0yL16E
  * String
  * 商户appid下，某用户的openid
   * </pre>
  */
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
  * 校验用户姓名选项
  * check_name
  * 是
  * OPTION_CHECK
  * String
  * NO_CHECK：不校验真实姓名 
  * FORCE_CHECK：强校验真实姓名（未实名认证的用户会校验失败，无法转账） 
  * OPTION_CHECK：针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
   * </pre>
  */
  @XStreamAlias("check_name")
  private String checkName;

  /**
   * <pre>
  * 收款用户姓名
  * re_user_name
  * 可选
  * 马花花
  * String
  * 收款用户真实姓名。
  * 如果check_name设置为FORCE_CHECK或OPTION_CHECK，  则必填用户真实姓名
   * </pre>
  */
  @XStreamAlias("re_user_name")
  private String reUserName;

  /**
   * <pre>
  * 金额
  * amount
  * 是
  * 10099
  * int
  * 企业付款金额， 单位为分
   * </pre>
  */
  @XStreamAlias("amount")
  private Integer amount;

  /**
   * <pre>
  * 企业付款描述信息
  * desc
  * 是
  * 理赔
  * String
  * 企业付款操作说明信息。必填。
   * </pre>
  */
  @XStreamAlias("desc")
  private String description;

  /**
   * <pre>
  * Ip地址
  * spbill_create_ip
  * 是
  * 192.168.0.1
  * String(32)
  * 调用接口的机器Ip地址
   * </pre>
  */
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;


  @Override
  public String getAppid() {
    return this.mchAppid;
  }

  @Override
  public void setAppid(String appid) {
    this.mchAppid = appid;
  }

  @Override
  public String getMchId() {
    return this.mchId;
  }

  @Override
  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getDeviceInfo() {
    return this.deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getPartnerTradeNo() {
    return this.partnerTradeNo;
  }

  public void setPartnerTradeNo(String partnerTradeNo) {
    this.partnerTradeNo = partnerTradeNo;
  }

  public String getOpenid() {
    return this.openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getCheckName() {
    return this.checkName;
  }

  public void setCheckName(String checkName) {
    this.checkName = checkName;
  }

  public String getReUserName() {
    return this.reUserName;
  }

  public void setReUserName(String reUserName) {
    this.reUserName = reUserName;
  }

  public Integer getAmount() {
    return this.amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSpbillCreateIp() {
    return this.spbillCreateIp;
  }

  public void setSpbillCreateIp(String spbillCreateIp) {
    this.spbillCreateIp = spbillCreateIp;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
