package me.chanjar.weixin.mp.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * WxMpSubscribeMsgEvent class
 *  订阅通知事件推送，与小程序一致
 * @author liuxy
 * created on  2022/12/30
 */
public class WxMpSubscribeMsgEvent {
  /**
   * https://developers.weixin.qq.com/doc/offiaccount/Subscription_Messages/api.html
   * */
  @Data
  @XStreamAlias("SubscribeMsgPopupEvent")
  @JacksonXmlRootElement(localName = "SubscribeMsgPopupEvent")
  public static class SubscribeMsgPopupEvent implements Serializable {
    private static final long serialVersionUID = 6329723189257161326L;
    @XStreamImplicit(itemFieldName = "List")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "List")
    private List<PopupEvent> list = new LinkedList<>();
  }

  @Data
  @XStreamAlias("SubscribeMsgChangeEvent")
  @JacksonXmlRootElement(localName = "SubscribeMsgChangeEvent")
  public static class SubscribeMsgChangeEvent implements Serializable {
    private static final long serialVersionUID = 7205686111539437751L;
    @XStreamImplicit(itemFieldName = "List")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "List")
    private List<ChangeEvent> list = new LinkedList<>();
  }

  @Data
  @XStreamAlias("SubscribeMsgSentEvent")
  @JacksonXmlRootElement(localName = "SubscribeMsgSentEvent")
  public static class SubscribeMsgSentEvent implements Serializable {
    private static final long serialVersionUID = 7305686111539437752L;
    @XStreamImplicit(itemFieldName = "List")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "List")
    private List<SentEvent> list = new LinkedList<>();
  }


  @Data
  public static class PopupEvent implements Serializable {
    private static final long serialVersionUID = 4934029303242387226L;
    /**
     * 模板id
     */
    @XStreamAlias("TemplateId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "TemplateId")
    @JacksonXmlCData
    private String templateId;
    /**
     * 订阅结果（accept接收；reject拒收）
     */
    @XStreamAlias("SubscribeStatusString")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "SubscribeStatusString")
    @JacksonXmlCData
    private String subscribeStatusString;
    /**
     * 弹框场景,1代表弹窗来自 H5 页面, 2代表弹窗来自图文消息
     */
    @XStreamAlias("PopupScene")
    @JacksonXmlProperty(localName = "PopupScene")
    private String popupScene;
  }

  @Data
  public static class ChangeEvent implements Serializable {
    private static final long serialVersionUID = 3523634146232757624L;
    /**
     * 模板id
     */
    @XStreamAlias("TemplateId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "TemplateId")
    @JacksonXmlCData
    private String templateId;
    /**
     * 订阅结果（accept接收；reject拒收）
     */
    @XStreamAlias("SubscribeStatusString")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "SubscribeStatusString")
    @JacksonXmlCData
    private String subscribeStatusString;
  }

  @Data
  public static class SentEvent implements Serializable {
    private static final long serialVersionUID = 1734478345463177940L;
    /**
     * 模板id
     */
    @XStreamAlias("TemplateId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "TemplateId")
    @JacksonXmlCData
    private String templateId;

    /**
     * 消息id
     */
    @XStreamAlias("MsgID")
    @JacksonXmlProperty(localName = "MsgID")
    private String msgId;

    /**
     * 推送结果状态码（0表示成功）
     */
    @XStreamAlias("ErrorCode")
    @JacksonXmlProperty(localName = "ErrorCode")
    private String errorCode;

    /**
     * 推送结果状态码文字含义
     */
    @XStreamAlias("ErrorStatus")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "ErrorStatus")
    @JacksonXmlCData
    private String errorStatus;
  }
}
