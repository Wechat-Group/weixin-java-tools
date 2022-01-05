package cn.binarywang.wx.miniapp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * WxMaSubscribeMsgEvent class
 *  客户端订阅，服务端收到的通知
 * @author dany
 * @date 2021/12/31
 */
public class WxMaSubscribeMsgEvent {
  /**
   * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/subscribe-message.html
   */
  @Data
  @XStreamAlias("SubscribeMsgPopupEvent")
  public static class SubscribeMsgPopupEvent implements Serializable {
    private static final long serialVersionUID = 6319723189257161326L;
    @XStreamImplicit(itemFieldName = "List")
    private List<PopupEvent> list = new LinkedList<>();
  }

  @Data
  @XStreamAlias("SubscribeMsgChangeEvent")
  public static class SubscribeMsgChangeEvent implements Serializable {
    private static final long serialVersionUID = 7705686111539437751L;
    @XStreamImplicit(itemFieldName = "List")
    private List<ChangeEvent> list = new LinkedList<>();
  }

  @Data
  @XStreamAlias("SubscribeMsgSentEvent")
  public static class SubscribeMsgSentEvent implements Serializable {
    private static final long serialVersionUID = 7705686111539437751L;
    @XStreamAlias("List")
    private SentEvent list;
  }


  @Data
  public static class PopupEvent implements Serializable {
    private static final long serialVersionUID = 4934029303241387226L;
    /**
     * 模板id
     */
    @XStreamAlias("TemplateId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String templateId;
    /**
     * 订阅结果（accept接收；reject拒收）
     */
    @XStreamAlias("SubscribeStatusString")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String subscribeStatusString;
    /**
     * 弹框场景，0代表在小程序页面内
     */
    @XStreamAlias("PopupScene")
    private String popupScene;
  }

  @Data
  public static class ChangeEvent implements Serializable {
    private static final long serialVersionUID = 1523634146232757624L;
    /**
     * 模板id
     */
    @XStreamAlias("TemplateId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String templateId;
    /**
     * 订阅结果（accept接收；reject拒收）
     */
    @XStreamAlias("SubscribeStatusString")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String subscribeStatusString;
  }

  @Data
  public static class SentEvent implements Serializable {
    private static final long serialVersionUID = -8734478345463177940L;
    /**
     * 模板id
     */
    @XStreamAlias("TemplateId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String templateId;

    @XStreamAlias("MsgID")
    private String msgId;

    @XStreamAlias("ErrorCode")
    private String errorCode;

    @XStreamAlias("ErrorStatus")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String errorStatus;
  }

  @Data
  public static class WxMaSubscribeMsgEventJson implements Serializable {
    private static final long serialVersionUID = -4820758280837190275L;

    private SubscribeMsgPopupEvent popupEvents;

    private SubscribeMsgChangeEvent changeEvents;

    private SubscribeMsgSentEvent sentEvent;
  }
}
