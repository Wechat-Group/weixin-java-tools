package com.github.binarywang.wxpay.bean.notify;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * 微信支付订单和退款的异步通知共用的响应类.
 *
 * @author someone
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayNotifyResponse {
  @XStreamOmitField
  private static final transient String FAIL = "FAIL";
  @XStreamOmitField
  private static final transient String SUCCESS = "SUCCESS";

  @XStreamAlias("return_code")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String returnCode;
  @XStreamConverter(value = XStreamCDataConverter.class)
  @XStreamAlias("return_msg")
  private String returnMsg;

  /**
   * Fail string.
   *
   * @param msg the msg
   * @return the string
   */
  public static String fail(String msg) {
    WxPayNotifyResponse response = new WxPayNotifyResponse(FAIL, msg);
    XStream xstream = XStreamInitializer.getInstance();
    xstream.autodetectAnnotations(true);
    return xstream.toXML(response).replace("\n", "").replace("  ", "");
  }

  /**
   * Success string.
   *
   * @param msg the msg
   * @return the string
   */
  public static String success(String msg) {
    WxPayNotifyResponse response = new WxPayNotifyResponse(SUCCESS, msg);
    XStream xstream = XStreamInitializer.getInstance();
    xstream.autodetectAnnotations(true);
    return xstream.toXML(response).replace("\n", "").replace("  ", "");
  }

}
