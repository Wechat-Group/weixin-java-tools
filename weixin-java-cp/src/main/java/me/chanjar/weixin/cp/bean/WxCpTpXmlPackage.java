package me.chanjar.weixin.cp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XmlUtil;

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
  protected String toUserName;

  @XStreamAlias("AgentID")
  protected String agentId;

  @XStreamAlias("Encrypt")
  protected String msgEncrypt;

  public static WxCpTpXmlPackage fromXml(String xml) {
    return XmlUtil.toObject(xml, WxCpTpXmlPackage.class);
  }
}
