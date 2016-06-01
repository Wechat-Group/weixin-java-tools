package me.chanjar.weixin.mp.bean.result;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 向微信用户个人发现金红包返回结果
 * https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5
 * @author kane
 *
 */
@XStreamAlias("xml")
public class WxRedpackResult implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -4837415036337132073L;

  @XStreamAlias("return_code")
  String returnCode;
  @XStreamAlias("return_msg")
  String returnMsg;
  @XStreamAlias("sign")
  String sign;
  @XStreamAlias("result_code")
  String resultCode;
  
  @XStreamAlias("err_code")
  String errCode;
  @XStreamAlias("err_code_des")
  String errCodeDes;
  @XStreamAlias("mch_billno")
  String mchBillno;
  @XStreamAlias("mch_id")
  String mchId;
  @XStreamAlias("wxappid")
  String wxappid;
  @XStreamAlias("re_openid")
  String reOpenid;
  @XStreamAlias("total_amount")
  int totalAmount;
  @XStreamAlias("send_time")
  String sendTime;
  @XStreamAlias("send_listid")
  String sendListid;
  
  public String getErrCode() {
    return errCode;
  }
  
  public String getErrCodeDes() {
    return errCodeDes;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public String getSign() {
    return sign;
  }

  public String getResultCode() {
    return resultCode;
  }

  public String getMchBillno() {
    return mchBillno;
  }

  public String getMchId() {
    return mchId;
  }

  public String getWxappid() {
    return wxappid;
  }

  public String getReOpenid() {
    return reOpenid;
  }

  public int getTotalAmount() {
    return totalAmount;
  }

  public String getSendTime() {
    return sendTime;
  }

  public String getSendListid() {
    return sendListid;
  }
  
  @Override
  public String toString() {
    return "WxRedpackResult{" +
        "returnCode=" + returnCode +
        ", returnMsg=" + returnMsg +
        ", sign=" + sign +
        ", errCode=" + errCode +
        ", errCodeDes=" + errCodeDes +
        ", mchBillno=" + mchBillno +
        ", mchId=" + mchId +
        ", wxappid=" + wxappid +
        ", reOpenid=" + reOpenid +
        ", totalAmount=" + totalAmount +
        ", sendTime=" + sendTime +
        ", sendListid=" + sendListid +
        '}';
  }
}
