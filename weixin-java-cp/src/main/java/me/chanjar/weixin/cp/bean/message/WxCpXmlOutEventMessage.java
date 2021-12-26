package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.*;

/**
 * @author eYoung
 * @description:
 * @date create at 2021/12/3 16:36
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutEventMessage extends WxCpXmlOutMessage {

  @XStreamAlias("Event")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String event;

  @XStreamAlias("ChatId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String chatId;

  @XStreamAlias("ChangeType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String changeType;

  @XStreamAlias("UpdateDetail")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String updateDetail;

  @XStreamAlias("JoinScene")
  private String joinScene;

  @XStreamAlias("QuitScene")
  private String quitScene;

  @XStreamAlias("MemChangeCnt")
  private String memChangeCnt;

  @XStreamAlias("TagType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String tagType;

  @XStreamAlias("StrategyId")
  private String strategyId;

  @XStreamAlias("UserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String userID;

  @XStreamAlias("ExternalUserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String externalUserID;

  @XStreamAlias("State")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String state;

  @XStreamAlias("WelcomeCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String welcomeCode;

  @XStreamAlias("Source")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String source;

  @XStreamAlias("FailReason")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String failReason;

  @XStreamAlias("Id")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String id;

  public WxCpXmlOutEventMessage() {
    this.msgType = WxConsts.XmlMsgType.EVENT;
  }
}
