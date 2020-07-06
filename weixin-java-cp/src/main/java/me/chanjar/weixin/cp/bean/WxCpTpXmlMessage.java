package me.chanjar.weixin.cp.bean;

import java.io.Serializable;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;

/**
 * 回调推送的message
 * https://work.weixin.qq.com/api/doc#90001/90143/90612
 *
 * @author zhenjun cai
 */
@XStreamAlias("xml")
@Slf4j
@Data
public class WxCpTpXmlMessage implements Serializable {

  private static final long serialVersionUID = 6031833682211475786L;
  /**
   * 使用dom4j解析的存放所有xml属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  @XStreamAlias("SuiteId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String suiteId;

  @XStreamAlias("InfoType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String infoType;

  @XStreamAlias("TimeStamp")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String timeStamp;

  @XStreamAlias("SuiteTicket")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String suiteTicket;

  @XStreamAlias("AuthCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String authCode;

  @XStreamAlias("AuthCorpId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String authCorpId;

  public static WxCpTpXmlMessage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    //xml = xml.replace("</PicList><PicList>", "");
    final WxCpTpXmlMessage xmlPackage = XStreamTransformer.fromXml(WxCpTpXmlMessage.class, xml);
    xmlPackage.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return xmlPackage;
  }

}
