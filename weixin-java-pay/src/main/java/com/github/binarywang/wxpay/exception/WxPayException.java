package com.github.binarywang.wxpay.exception;

import com.github.binarywang.wxpay.bean.result.WxPayBaseResult;
import com.google.common.base.Joiner;

/**
 * <pre>
 * 微信支付异常结果类
 * Created by Binary Wang on 2017-6-6.
 * </pre>
 */
public class WxPayException extends Exception {
  private String customErrorMsg;
  /**
   * 返回状态码
   */
  private String returnCode;
  /**
   * 返回信息
   */
  private String returnMsg;

  /**
   * 业务结果
   */
  private String resultCode;

  /**
   * 错误代码
   */
  private String errCode;

  /**
   * 错误代码描述
   */
  private String errCodeDes;

  /**
   * 微信支付返回的结果xml字符串
   */
  private String xmlString;

  public WxPayException(String customErrorMsg) {
    super(customErrorMsg);
    this.customErrorMsg = customErrorMsg;
  }

  public WxPayException(String customErrorMsg, Throwable tr) {
    super(customErrorMsg, tr);
    this.customErrorMsg = customErrorMsg;
  }

  private WxPayException(Builder builder) {
    super(builder.buildErrorMsg());
    returnCode = builder.returnCode;
    returnMsg = builder.returnMsg;
    resultCode = builder.resultCode;
    errCode = builder.errCode;
    errCodeDes = builder.errCodeDes;
    xmlString = builder.xmlString;
  }

  public static WxPayException from(WxPayBaseResult payBaseResult) {
    return WxPayException.newBuilder()
      .xmlString(payBaseResult.getXmlString())
      .returnMsg(payBaseResult.getReturnMsg())
      .returnCode(payBaseResult.getReturnCode())
      .resultCode(payBaseResult.getResultCode())
      .errCode(payBaseResult.getErrCode())
      .errCodeDes(payBaseResult.getErrCodeDes())
      .build();
  }

  public String getXmlString() {
    return this.xmlString;
  }

  public String getReturnCode() {
    return this.returnCode;
  }

  public String getReturnMsg() {
    return this.returnMsg;
  }

  public String getResultCode() {
    return this.resultCode;
  }

  public String getErrCode() {
    return this.errCode;
  }

  public String getErrCodeDes() {
    return this.errCodeDes;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private String returnCode;
    private String returnMsg;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    private String xmlString;

    private Builder() {
    }

    public Builder returnCode(String returnCode) {
      this.returnCode = returnCode;
      return this;
    }

    public Builder returnMsg(String returnMsg) {
      this.returnMsg = returnMsg;
      return this;
    }

    public Builder resultCode(String resultCode) {
      this.resultCode = resultCode;
      return this;
    }

    public Builder errCode(String errCode) {
      this.errCode = errCode;
      return this;
    }

    public Builder errCodeDes(String errCodeDes) {
      this.errCodeDes = errCodeDes;
      return this;
    }

    public Builder xmlString(String xmlString) {
      this.xmlString = xmlString;
      return this;
    }

    public WxPayException build() {
      return new WxPayException(this);
    }

    public String buildErrorMsg() {
      return Joiner.on("，").skipNulls().join(new String[]{
        returnCode == null ? null : String.format("返回代码：[%s]", returnCode),
        returnMsg == null ? null : String.format("返回信息：[%s]", returnMsg),
        resultCode == null ? null : String.format("结果代码：[%s]", resultCode),
        errCode == null ? null : String.format("错误代码：[%s]", errCode),
        errCodeDes == null ? null : String.format("错误详情：[%s]", errCodeDes),
        xmlString == null ? null : "微信返回的原始报文：\n" + xmlString,
      });
    }
  }
}
