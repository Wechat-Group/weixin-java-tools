package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * 撤销订单请求类
 * Created by Binary Wang on 2017-3-23.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayOrderReverseRequest extends WxPayBaseRequest {

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * String(28)
   * 1217752501201400000000000000
   * 微信的订单号，优先使用
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * String(32)
   * 1217752501201400000000000000
   * 商户系统内部的订单号
   * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 签名类型
   * sign_type
   * 否
   * String(32)
   * HMAC-SHA256
   * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
   **/
  @XStreamAlias("sign_type")
  private String signType;

  private WxPayOrderReverseRequest(Builder builder) {
    setTransactionId(builder.transactionId);
    setAppid(builder.appid);
    setOutTradeNo(builder.outTradeNo);
    setMchId(builder.mchId);
    setSignType(builder.signType);
    setSubAppId(builder.subAppId);
    setSubMchId(builder.subMchId);
    setNonceStr(builder.nonceStr);
    setSign(builder.sign);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getOutTradeNo() {
    return this.outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getSignType() {
    return this.signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  @Override
  protected void checkConstraints() {
    if (StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)) {
      throw new IllegalArgumentException("transaction_id 和 out_trade_no不能同时为空！");
    }
  }

  public static final class Builder {
    private String transactionId;
    private String appid;
    private String outTradeNo;
    private String mchId;
    private String signType;
    private String subAppId;
    private String subMchId;
    private String nonceStr;
    private String sign;

    private Builder() {
    }

    public Builder transactionId(String transactionId) {
      this.transactionId = transactionId;
      return this;
    }

    public Builder appid(String appid) {
      this.appid = appid;
      return this;
    }

    public Builder outTradeNo(String outTradeNo) {
      this.outTradeNo = outTradeNo;
      return this;
    }

    public Builder mchId(String mchId) {
      this.mchId = mchId;
      return this;
    }

    public Builder signType(String signType) {
      this.signType = signType;
      return this;
    }

    public Builder subAppId(String subAppId) {
      this.subAppId = subAppId;
      return this;
    }

    public Builder subMchId(String subMchId) {
      this.subMchId = subMchId;
      return this;
    }

    public Builder nonceStr(String nonceStr) {
      this.nonceStr = nonceStr;
      return this;
    }

    public Builder sign(String sign) {
      this.sign = sign;
      return this;
    }

    public WxPayOrderReverseRequest build() {
      return new WxPayOrderReverseRequest(this);
    }
  }
}
