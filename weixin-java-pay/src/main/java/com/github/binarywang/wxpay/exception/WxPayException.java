package com.github.binarywang.wxpay.exception;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.google.common.base.Joiner;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 微信支付异常结果类
 * Created by Binary Wang on 2017-6-6.
 * </pre>
 *
 * @author BinaryWang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxPayException extends Exception {
  private static final long serialVersionUID = 2214381471513460742L;

  /**
   * 自定义错误讯息.
   */
  private String customErrorMsg;
  /**
   * 返回状态码.
   */
  private String returnCode;
  /**
   * 返回信息.
   */
  private String returnMsg;

  /**
   * 业务结果.
   */
  private String resultCode;

  /**
   * 错误代码.
   */
  private String errCode;

  /**
   * 错误代码描述.
   */
  private String errCodeDes;

  /**
   * 微信支付返回的结果xml字符串.
   */
  private String xmlString;

  /**
   * Instantiates a new Wx pay exception.
   *
   * @param customErrorMsg the custom error msg
   */
  public WxPayException(String customErrorMsg) {
    super(customErrorMsg);
    this.customErrorMsg = customErrorMsg;
  }

  /**
   * Instantiates a new Wx pay exception.
   *
   * @param customErrorMsg the custom error msg
   * @param tr             the tr
   */
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

  /**
   * 通过BaseWxPayResult生成异常对象.
   *
   * @param payBaseResult the pay base result
   * @return the wx pay exception
   */
  public static WxPayException from(BaseWxPayResult payBaseResult) {
    return WxPayException.newBuilder()
      .xmlString(payBaseResult.getXmlString())
      .returnMsg(payBaseResult.getReturnMsg())
      .returnCode(payBaseResult.getReturnCode())
      .resultCode(payBaseResult.getResultCode())
      .errCode(payBaseResult.getErrCode())
      .errCodeDes(payBaseResult.getErrCodeDes())
      .build();
  }

  /**
   * New builder builder.
   *
   * @return the builder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * The type Builder.
   */
  public static final class Builder {
    private String returnCode;
    private String returnMsg;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    private String xmlString;

    private Builder() {
    }

    /**
     * Return code builder.
     *
     * @param returnCode the return code
     * @return the builder
     */
    public Builder returnCode(String returnCode) {
      this.returnCode = returnCode;
      return this;
    }

    /**
     * Return msg builder.
     *
     * @param returnMsg the return msg
     * @return the builder
     */
    public Builder returnMsg(String returnMsg) {
      this.returnMsg = returnMsg;
      return this;
    }

    /**
     * Result code builder.
     *
     * @param resultCode the result code
     * @return the builder
     */
    public Builder resultCode(String resultCode) {
      this.resultCode = resultCode;
      return this;
    }

    /**
     * Err code builder.
     *
     * @param errCode the err code
     * @return the builder
     */
    public Builder errCode(String errCode) {
      this.errCode = errCode;
      return this;
    }

    /**
     * Err code des builder.
     *
     * @param errCodeDes the err code des
     * @return the builder
     */
    public Builder errCodeDes(String errCodeDes) {
      this.errCodeDes = errCodeDes;
      return this;
    }

    /**
     * Xml string builder.
     *
     * @param xmlString the xml string
     * @return the builder
     */
    public Builder xmlString(String xmlString) {
      this.xmlString = xmlString;
      return this;
    }

    /**
     * Build wx pay exception.
     *
     * @return the wx pay exception
     */
    public WxPayException build() {
      return new WxPayException(this);
    }

    /**
     * Build error msg string.
     *
     * @return the string
     */
    public String buildErrorMsg() {
      return Joiner.on("，").skipNulls().join(
        returnCode == null ? null : String.format("返回代码：[%s]", returnCode),
        returnMsg == null ? null : String.format("返回信息：[%s]", returnMsg),
        resultCode == null ? null : String.format("结果代码：[%s]", resultCode),
        errCode == null ? null : String.format("错误代码：[%s]", errCode),
        errCodeDes == null ? null : String.format("错误详情：[%s]", errCodeDes),
        xmlString == null ? null : "微信返回的原始报文：\n" + xmlString
      );
    }
  }
}
