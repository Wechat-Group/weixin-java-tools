package me.chanjar.weixin.cp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;

import java.io.Serializable;
import java.util.Map;

/**
 * 回调消息包.
 * https://work.weixin.qq.com/api/doc#90001/90143/91116
 *
 * @author zhenjun cai
 */
@XStreamAlias("xml")
@Data
public class WxCpTpXmlPackage implements Serializable {
  private static final long serialVersionUID = 6031833682211475786L;

  /**
   * 使用dom4j解析的存放所有xml属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String toUserName;

  @XStreamAlias("AgentID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String agentId;

  @XStreamAlias("Encrypt")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String msgEncrypt;

  public static WxCpTpXmlPackage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    //xml = xml.replace("</PicList><PicList>", "");
    final WxCpTpXmlPackage xmlPackage = XStreamTransformer.fromXml(WxCpTpXmlPackage.class, xml);
    xmlPackage.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return xmlPackage;
  }
}
