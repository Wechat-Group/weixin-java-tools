package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 *  提交刷卡支付请求对象类
 * Created by Binary Wang on 2017-3-23.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayMicropayRequest extends WxPayBaseRequest {
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

  /**
   * <pre>
   * 商品描述
   * body
   * 是
   * String(128)
   * image形象店-深圳腾大- QQ公仔
   * 商品简单描述，该字段须严格按照规范传递，具体请见参数规定
   **/
  @Required
  @XStreamAlias("body")
  private String body;

  /**
   * <pre>
   * 商品详情
   * detail
   * 否
   * String(6000)
   *
   * 单品优惠功能字段，需要接入请见详细说明
   **/
  @XStreamAlias("detail")
  private String detail;

  /**
   * <pre>
   * 附加数据
   * attach
   * 否
   * String(127)
   * 说明
   * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
   **/
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的订单号,32个字符内、可包含字母,其他说明见商户订单号
   **/
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 订单金额
   * total_fee
   * 是
   * Int
   * 888
   * 订单总金额，单位为分，只能为整数，详见支付金额
   **/
  @Required
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 货币类型
   * fee_type
   * 否
   * String(16)
   * CNY
   * 符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   **/
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>
   * 终端IP
   * spbill_create_ip
   * 是
   * String(16)
   * 8.8.8.8
   * 调用微信支付API的机器IP
   **/
  @Required
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

  /**
   * <pre>
   * 商品标记
   * goods_tag
   * 否
   * String(32)
   * 1234
   * 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
   **/
  @XStreamAlias("goods_tag")
  private String goodsTag;

  /**
   * <pre>
   * 指定支付方式
   * limit_pay
   * 否
   * String(32)
   * no_credit
   * no_credit--指定不能使用信用卡支付
   **/
  @XStreamAlias("limit_pay")
  private String limitPay;

  /**
   * <pre>
   * 授权码
   * auth_code
   * 是
   * String(128)
   * 120061098828009406
   * 扫码支付授权码，设备读取用户微信中的条码或者二维码信息注：用户刷卡条形码规则：18位纯数字，以10、11、12、13、14、15开头）
   **/
  @Required
  @XStreamAlias("auth_code")
  private String authCode;

  private WxPayMicropayRequest(Builder builder) {
    setSignType(builder.signType);
    setBody(builder.body);
    setAppid(builder.appid);
    setDetail(builder.detail);
    setMchId(builder.mchId);
    setAttach(builder.attach);
    setSubAppId(builder.subAppId);
    setOutTradeNo(builder.outTradeNo);
    setSubMchId(builder.subMchId);
    setTotalFee(builder.totalFee);
    setNonceStr(builder.nonceStr);
    setFeeType(builder.feeType);
    setSign(builder.sign);
    setSpbillCreateIp(builder.spbillCreateIp);
    setGoodsTag(builder.goodsTag);
    setLimitPay(builder.limitPay);
    setAuthCode(builder.authCode);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getSignType() {
    return this.signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getDetail() {
    return this.detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getAttach() {
    return this.attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getOutTradeNo() {
    return this.outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public Integer getTotalFee() {
    return this.totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public String getFeeType() {
    return this.feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public String getSpbillCreateIp() {
    return this.spbillCreateIp;
  }

  public void setSpbillCreateIp(String spbillCreateIp) {
    this.spbillCreateIp = spbillCreateIp;
  }

  public String getGoodsTag() {
    return this.goodsTag;
  }

  public void setGoodsTag(String goodsTag) {
    this.goodsTag = goodsTag;
  }

  public String getLimitPay() {
    return this.limitPay;
  }

  public void setLimitPay(String limitPay) {
    this.limitPay = limitPay;
  }

  public String getAuthCode() {
    return this.authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  @Override
  protected void checkConstraints() {
    //do nothing
  }

  public static final class Builder {
    private String signType;
    private String body;
    private String appid;
    private String detail;
    private String mchId;
    private String attach;
    private String subAppId;
    private String outTradeNo;
    private String subMchId;
    private Integer totalFee;
    private String nonceStr;
    private String feeType;
    private String sign;
    private String spbillCreateIp;
    private String goodsTag;
    private String limitPay;
    private String authCode;

    private Builder() {
    }

    public Builder signType(String signType) {
      this.signType = signType;
      return this;
    }

    public Builder body(String body) {
      this.body = body;
      return this;
    }

    public Builder appid(String appid) {
      this.appid = appid;
      return this;
    }

    public Builder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public Builder mchId(String mchId) {
      this.mchId = mchId;
      return this;
    }

    public Builder attach(String attach) {
      this.attach = attach;
      return this;
    }

    public Builder subAppId(String subAppId) {
      this.subAppId = subAppId;
      return this;
    }

    public Builder outTradeNo(String outTradeNo) {
      this.outTradeNo = outTradeNo;
      return this;
    }

    public Builder subMchId(String subMchId) {
      this.subMchId = subMchId;
      return this;
    }

    public Builder totalFee(Integer totalFee) {
      this.totalFee = totalFee;
      return this;
    }

    public Builder nonceStr(String nonceStr) {
      this.nonceStr = nonceStr;
      return this;
    }

    public Builder feeType(String feeType) {
      this.feeType = feeType;
      return this;
    }

    public Builder sign(String sign) {
      this.sign = sign;
      return this;
    }

    public Builder spbillCreateIp(String spbillCreateIp) {
      this.spbillCreateIp = spbillCreateIp;
      return this;
    }

    public Builder goodsTag(String goodsTag) {
      this.goodsTag = goodsTag;
      return this;
    }

    public Builder limitPay(String limitPay) {
      this.limitPay = limitPay;
      return this;
    }

    public Builder authCode(String authCode) {
      this.authCode = authCode;
      return this;
    }

    public WxPayMicropayRequest build() {
      return new WxPayMicropayRequest(this);
    }
  }
}
