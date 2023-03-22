package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.cp.bean.outxmlbuilder.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;

import java.io.Serializable;

/**
 * 被动回复消息.
 * https://work.weixin.qq.com/api/doc#12975
 *
 * @author Daniel Qian
 */
@XStreamAlias("xml")
@Data
public abstract class WxCpXmlOutMessage implements Serializable {
  private static final long serialVersionUID = 1418629839964153110L;

  /**
   * The To user name.
   */
  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String toUserName;

  /**
   * The From user name.
   */
  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String fromUserName;

  /**
   * The Create time.
   */
  @XStreamAlias("CreateTime")
  protected Long createTime;

  /**
   * The Msg type.
   */
  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  protected String msgType;

  /**
   * 获得文本消息builder.
   *
   * @return the text builder
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder.
   *
   * @return the image builder
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder.
   *
   * @return the voice builder
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder.
   *
   * @return the video builder
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得图文消息builder.
   *
   * @return the news builder
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得任务卡片消息builder.
   *
   * @return the task card builder
   */
  public static TaskCardBuilder TASK_CARD() {
    return new TaskCardBuilder();
  }

  /**
   * 获得任务卡片消息builder.
   *
   * @return the update button builder
   */
  public static UpdateButtonBuilder UPDATE_BUTTON() {
    return new UpdateButtonBuilder();
  }

  /**
   * 获得事件消息builder.
   *
   * @return the event builder
   */
  public static EventBuilder EVENT() {
    return new EventBuilder();
  }

  /**
   * To xml string.
   *
   * @return the string
   */
  protected String toXml() {
    return XStreamTransformer.toXml((Class) this.getClass(), this);
  }

  /**
   * 转换成加密的xml格式.
   *
   * @param wxCpConfigStorage the wx cp config storage
   * @return the string
   */
  public String toEncryptedXml(WxCpConfigStorage wxCpConfigStorage) {
    String plainXml = toXml();
    WxCpCryptUtil pc = new WxCpCryptUtil(wxCpConfigStorage);
    return pc.encrypt(plainXml);
  }

  /**
   * 企业微信服务商 转换加密的xml 格式
   * @param wxCpTpConfigStorage th wx cp tp config storage
   * @return the string
   */
  public String toEncryptedXml(WxCpTpConfigStorage wxCpTpConfigStorage) {
    String plainXml = toXml();
    WxCpTpCryptUtil pc = new WxCpTpCryptUtil(wxCpTpConfigStorage);
    return pc.encrypt(plainXml);
  }
}
