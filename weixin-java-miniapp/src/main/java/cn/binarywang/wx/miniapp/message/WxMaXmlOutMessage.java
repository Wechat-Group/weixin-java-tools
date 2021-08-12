package cn.binarywang.wx.miniapp.message;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.util.xml.XmlUtil;

import java.io.Serializable;

/**
 * 微信小程序输出给微信服务器的消息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-22
 */
@Data
@XStreamAlias("xml")
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxMaXmlOutMessage implements Serializable {
  private static final long serialVersionUID = 4241135225946919153L;

  @XStreamAlias("ToUserName")
  protected String toUserName;

  @XStreamAlias("FromUserName")
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  protected String msgType;

  @SuppressWarnings("unchecked")
  public String toXml() {
    return XmlUtil.toXml(this);
  }

  /**
   * 转换成加密的xml格式.
   */
  public String toEncryptedXml(WxMaConfig config) {
    String plainXml = toXml();
    WxMaCryptUtils pc = new WxMaCryptUtils(config);
    return pc.encrypt(plainXml);
  }
}
