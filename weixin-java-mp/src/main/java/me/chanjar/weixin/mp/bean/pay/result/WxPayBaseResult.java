package me.chanjar.weixin.mp.bean.pay.result;

import java.math.BigDecimal;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * <pre>
 * 微信支付结果共用属性类
 * Created by Binary Wang on 2016-10-24.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public abstract class WxPayBaseResult {
  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  /**
   * 返回状态码
   */
  @XStreamAlias("return_code")
  protected String returnCode;

  /**
   * 返回信息
   */
  @XStreamAlias("return_msg")
  protected String returnMsg;

  /**
   * 业务结果
   */
  @XStreamAlias("result_code")
  private String resultCode;

  /**
   * 错误代码
   */
  @XStreamAlias("err_code")
  private String errCode;

  /**
   * 错误代码描述
   */
  @XStreamAlias("err_code_des")
  private String errCodeDes;

  /**
   * 公众账号ID
   */
  @XStreamAlias("appid")
  private String appid;

  /**
   * 商户号
   */
  @XStreamAlias("mch_id")
  private String mchId;

  /**
   * 随机字符串
   */
  @XStreamAlias("nonce_str")
  private String nonceStr;

  /**
   * 签名
   */
  @XStreamAlias("sign")
  private String sign;

  public String getReturnCode() {
    return this.returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMsg() {
    return this.returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }

  public String getResultCode() {
    return this.resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getErrCode() {
    return this.errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getErrCodeDes() {
    return this.errCodeDes;
  }

  public void setErrCodeDes(String errCodeDes) {
    this.errCodeDes = errCodeDes;
  }

  public String getAppid() {
    return this.appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getMchId() {
    return this.mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getNonceStr() {
    return this.nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getSign() {
    return this.sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
  
  /**
   * 将单位分转换成单位圆
   * @param fee
   * @return
   */
  public static String feeToYuan(Integer fee) {
    return new BigDecimal(Double.valueOf(fee) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
  }
  
  public Map<String,String> toMap(){
  	return BeanUtils.xmlBean2Map(this);
  }
}
