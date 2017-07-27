package com.github.binarywang.wxpay.bean.coupon;

import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 * 查询代金券批次请求对象类
 * Created by Binary Wang on 2017-7-15.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@XStreamAlias("xml")
public class WxPayCouponStockQueryRequest extends WxPayBaseRequest {
  /**
   * <pre>
   * 字段名：代金券批次id
   * 变量名：coupon_stock_id
   * 是否必填：是
   * 示例值：1757
   * 类型：String
   * 说明：代金券批次id
   * </pre>
   */
  @Required
  @XStreamAlias("coupon_stock_id")
  private String couponStockId;

  /**
   * <pre>
   * 字段名：操作员
   * 变量名：op_user_id
   * 是否必填：否
   * 示例值：10000098
   * 类型：String(32)
   * 说明：操作员帐号, 默认为商户号,可在商户平台配置操作员对应的api权限
   * </pre>
   */
  @XStreamAlias("op_user_id")
  private String opUserId;

  /**
   * <pre>
   * 字段名：设备号
   * 变量名：device_info
   * 是否必填：否
   * 示例值：
   * 类型：String(32)
   * 说明：微信支付分配的终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 字段名：协议版本
   * 变量名：version
   * 是否必填：否
   * 示例值：1.0
   * 类型：String(32)
   * 说明：默认1.0
   * </pre>
   */
  @XStreamAlias("version")
  private String version;

  /**
   * <pre>
   * 字段名：协议类型
   * 变量名：type
   * 是否必填：否
   * 示例值：XML
   * 类型：String(32)
   * 说明：XML【目前仅支持默认XML】
   * </pre>
   */
  @XStreamAlias("type")
  private String type;

  private WxPayCouponStockQueryRequest(Builder builder) {
    setAppid(builder.appid);
    setMchId(builder.mchId);
    setSubAppId(builder.subAppId);
    setSubMchId(builder.subMchId);
    setNonceStr(builder.nonceStr);
    setSign(builder.sign);
    setCouponStockId(builder.couponStockId);
    setOpUserId(builder.opUserId);
    setDeviceInfo(builder.deviceInfo);
    setVersion(builder.version);
    setType(builder.type);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getCouponStockId() {
    return this.couponStockId;
  }

  public void setCouponStockId(String couponStockId) {
    this.couponStockId = couponStockId;
  }

  public String getOpUserId() {
    return this.opUserId;
  }

  public void setOpUserId(String opUserId) {
    this.opUserId = opUserId;
  }

  public String getDeviceInfo() {
    return this.deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getVersion() {
    return this.version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  protected void checkConstraints() {
    //do nothing
  }

  public static final class Builder {
    private String appid;
    private String mchId;
    private String subAppId;
    private String subMchId;
    private String nonceStr;
    private String sign;
    private String couponStockId;
    private String opUserId;
    private String deviceInfo;
    private String version;
    private String type;

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

    public Builder couponStockId(String couponStockId) {
      this.couponStockId = couponStockId;
      return this;
    }

    public Builder opUserId(String opUserId) {
      this.opUserId = opUserId;
      return this;
    }

    public Builder deviceInfo(String deviceInfo) {
      this.deviceInfo = deviceInfo;
      return this;
    }

    public Builder version(String version) {
      this.version = version;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public WxPayCouponStockQueryRequest build() {
      return new WxPayCouponStockQueryRequest(this);
    }
  }
}
