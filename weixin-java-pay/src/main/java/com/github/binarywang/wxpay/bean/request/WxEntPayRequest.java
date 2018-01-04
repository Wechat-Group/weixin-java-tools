package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.bean.entpay.EntPayQueryRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * <pre>
 * 企业付款请求对象
 * 请使用 {@link EntPayRequest}
 * </pre>
 */
@XStreamAlias("xml")
@Deprecated
public class WxEntPayRequest extends EntPayRequest {

  private WxEntPayRequest(Builder builder) {
    setAppid(builder.appid);
    setMchId(builder.mchId);
    setSubAppId(builder.subAppId);
    setSubMchId(builder.subMchId);
    setNonceStr(builder.nonceStr);
    setSign(builder.sign);
    setSignType(builder.signType);
    setMchAppid(builder.mchAppid);
    setMchId(builder.mchId);
    setDeviceInfo(builder.deviceInfo);
    setPartnerTradeNo(builder.partnerTradeNo);
    setOpenid(builder.openid);
    setCheckName(builder.checkName);
    setReUserName(builder.reUserName);
    setAmount(builder.amount);
    setDescription(builder.description);
    setSpbillCreateIp(builder.spbillCreateIp);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String appid;
    private String mchId;
    private String deviceInfo;
    private String partnerTradeNo;
    private String openid;
    private String checkName;
    private String reUserName;
    private Integer amount;
    private String description;
    private String spbillCreateIp;
    private String subAppId;
    private String subMchId;
    private String nonceStr;
    private String sign;
    private String signType;
    private String mchAppid;

    private Builder() {
    }

    public Builder appid(String appid) {
      this.appid = appid;
      return this;
    }

    public Builder mchId(String mchId) {
      this.mchId = mchId;
      return this;
    }

    public Builder deviceInfo(String deviceInfo) {
      this.deviceInfo = deviceInfo;
      return this;
    }

    public Builder partnerTradeNo(String partnerTradeNo) {
      this.partnerTradeNo = partnerTradeNo;
      return this;
    }

    public Builder openid(String openid) {
      this.openid = openid;
      return this;
    }

    public Builder checkName(String checkName) {
      this.checkName = checkName;
      return this;
    }

    public Builder reUserName(String reUserName) {
      this.reUserName = reUserName;
      return this;
    }

    public Builder amount(Integer amount) {
      this.amount = amount;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder spbillCreateIp(String spbillCreateIp) {
      this.spbillCreateIp = spbillCreateIp;
      return this;
    }

    public WxEntPayRequest build() {
      return new WxEntPayRequest(this);
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

    public Builder signType(String signType) {
      this.signType = signType;
      return this;
    }

    public Builder mchAppid(String mchAppid) {
      this.mchAppid = mchAppid;
      return this;
    }
  }
}
