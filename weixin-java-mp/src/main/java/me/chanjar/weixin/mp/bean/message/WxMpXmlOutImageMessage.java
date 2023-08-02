package me.chanjar.weixin.mp.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamMediaIdConverter;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutImageMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -2684778597067990723L;

  @XStreamAlias("Image")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  @JacksonXmlProperty(localName = "Image")
  @JacksonXmlCData
  private String mediaId;

  public WxMpXmlOutImageMessage() {
    this.msgType = WxConsts.XmlMsgType.IMAGE;
  }

}
