package me.chanjar.weixin.mp.bean.message;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * <pre>
 *  Created by BinaryWang on 2017/5/4.
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@XStreamAlias("SendLocationInfo")
@JacksonXmlRootElement(localName = "SendLocationInfo")
public class SendLocationInfo implements Serializable {
  private static final long serialVersionUID = 6633214140499161130L;

  @XStreamAlias("Location_X")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Location_X")
  @JacksonXmlCData
  private String locationX;

  @XStreamAlias("Location_Y")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Location_Y")
  @JacksonXmlCData
  private String locationY;

  @XStreamAlias("Scale")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Scale")
  @JacksonXmlCData
  private String scale;

  @XStreamAlias("Label")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Label")
  @JacksonXmlCData
  private String label;

  @XStreamAlias("Poiname")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Poiname")
  @JacksonXmlCData
  private String poiName;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
