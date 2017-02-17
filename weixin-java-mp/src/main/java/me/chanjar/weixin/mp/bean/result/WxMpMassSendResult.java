package me.chanjar.weixin.mp.bean.result;

import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 群发消息一发送就返回的结果
 *
 * 真正的群发消息是否发送成功要看
 * http://mp.weixin.qq.com/wiki/index.php?title=高级群发接口#.E4.BA.8B.E4.BB.B6.E6.8E.A8.E9.80.81.E7.BE.A4.E5.8F.91.E7.BB.93.E6.9E.9C
 *
 * </pre>
 * @author chanjarster
 *
 */
public class WxMpMassSendResult implements Serializable {
  private static final long serialVersionUID = -4816336807575562818L;
  private String errorCode;
  private String errorMsg;
  private String msgId;
  private String msgDataId;

  public String getErrorCode() {
    return this.errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return this.errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String getMsgId() {
    return this.msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public String getMsgDataId() {
    return this.msgDataId;
  }

  public void setMsgDataId(String msgDataId) {
    this.msgDataId = msgDataId;
  }

  public static WxMpMassSendResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMassSendResult.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
