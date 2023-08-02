package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamReplaceNameConverter;

/**
 * The type Wx cp xml out task card message.
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutTaskCardMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = 7028014900972827324L;

  @XStreamAlias("TaskCard")
  @XStreamConverter(value = XStreamReplaceNameConverter.class)
  private String replaceName;

  /**
   * Instantiates a new Wx cp xml out task card message.
   */
  public WxCpXmlOutTaskCardMessage() {
    this.msgType = WxConsts.XmlMsgType.UPDATE_TASKCARD;
  }

}
