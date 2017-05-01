package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.annotation.Required;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * <pre>
 *   微信支付下载对账单请求参数类
 * Created by Binary Wang on 2017-01-11.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayDownloadBillRequest extends WxPayBaseRequest {
  private static final String[] BILL_TYPE = new String[]{"ALL", "REFUND", "SUCCESS"};

  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 13467007045764
   * 终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 签名类型
   * sign_type
   * 否
   * String(32)
   * HMAC-SHA256
   * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
   * </pre>
   */
  @XStreamAlias("sign_type")
  private String signType;

  /**
   * <pre>
   * 账单类型
   * bill_type
   * 是
   * ALL
   * String(8)
   * --ALL，返回当日所有订单信息，默认值
   * --SUCCESS，返回当日成功支付的订单
   * --REFUND，返回当日退款订单
   * </pre>
   */
  @Required
  @XStreamAlias("bill_type")
  private String billType;

  /**
   * <pre>
   * 对账单日期
   * bill_date
   * 是
   * String(8)
   * 20140603
   * 下载对账单的日期，格式：20140603
   * </pre>
   */
  @Required
  @XStreamAlias("bill_date")
  private String billDate;

  /**
   * <pre>
   * 压缩账单
   * tar_type
   * 否
   * String(8)
   * GZIP
   * 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
   * </pre>
   */
  @XStreamAlias("tar_type")
  private String tarType;

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getBillType() {
    return billType;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }

  public String getBillDate() {
    return billDate;
  }

  public void setBillDate(String billDate) {
    this.billDate = billDate;
  }

  public String getTarType() {
    return tarType;
  }

  public void setTarType(String tarType) {
    this.tarType = tarType;
  }

  @Override
  protected void checkConstraints() {
    if (StringUtils.isNotBlank(this.getTarType()) && !"GZIP".equals(this.getTarType())) {
      throw new IllegalArgumentException("tar_type值如果存在，只能为GZIP");
    }

    if (!ArrayUtils.contains(BILL_TYPE, this.getBillType())) {
      throw new IllegalArgumentException(String.format("bill_tpye目前必须为%s其中之一,实际值：%s",
        Arrays.toString(BILL_TYPE), this.getBillType()));
    }
  }
}
