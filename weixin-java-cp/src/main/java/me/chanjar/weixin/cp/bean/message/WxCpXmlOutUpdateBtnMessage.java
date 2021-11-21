package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.common.util.xml.XStreamMediaIdConverter;
import me.chanjar.weixin.common.util.xml.XStreamReplaceNameConverter;

import java.io.Serializable;

/**
 * @author nickname263
 * @date 2021-09-23
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutUpdateBtnMessage  extends WxCpXmlOutMessage {
  private static final long serialVersionUID = 976182367423048138L;
  @XStreamAlias("Button")
  @XStreamConverter(value = XStreamReplaceNameConverter.class)
  private String replaceName;

  public WxCpXmlOutUpdateBtnMessage() {
    this.msgType = WxConsts.XmlMsgType.UPDATE_BUTTON;
  }



}
