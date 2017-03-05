package com.github.binarywang.wxpay.bean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

@XStreamAlias("xml")
public class WxPayOrderNotifyResponse {
  @XStreamOmitField
  private transient static final String FAIL = "FAIL";
  @XStreamOmitField
  private transient static final String SUCCESS = "SUCCESS";

  @XStreamAlias("return_code")
  private String returnCode;
  @XStreamAlias("return_msg")
  private String returnMsg;

  public WxPayOrderNotifyResponse() {
    super();
  }

  public WxPayOrderNotifyResponse(String returnCode, String returnMsg) {
    super();
    this.returnCode = returnCode;
    this.returnMsg = returnMsg;
  }

  public static String fail(String msg) {
    WxPayOrderNotifyResponse response = new WxPayOrderNotifyResponse(FAIL, msg);
    XStream xstream = XStreamInitializer.getInstance();
    xstream.autodetectAnnotations(true);
    return xstream.toXML(response);
  }

  public static String success(String msg) {
    WxPayOrderNotifyResponse response = new WxPayOrderNotifyResponse(SUCCESS, msg);
    XStream xstream = XStreamInitializer.getInstance();
    xstream.autodetectAnnotations(true);
    return xstream.toXML(response);
  }

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }
}
