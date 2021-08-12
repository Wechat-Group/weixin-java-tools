package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;

@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutDeviceMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -3093843149649157587L;

  @XStreamAlias("DeviceType")
  private String deviceType;

  @XStreamAlias("DeviceID")
  private String deviceId;

  @XStreamAlias("Content")
  private String content;

  @XStreamAlias("SessionID")
  private String sessionId;

  public WxMpXmlOutDeviceMessage() {
    this.msgType = WxConsts.XmlMsgType.DEVICE_TEXT;
  }
}
