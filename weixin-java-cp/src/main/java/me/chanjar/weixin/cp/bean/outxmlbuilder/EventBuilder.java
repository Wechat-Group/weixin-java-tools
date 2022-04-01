package me.chanjar.weixin.cp.bean.outxmlbuilder;

import me.chanjar.weixin.cp.bean.message.WxCpXmlOutEventMessage;

/**
 * @author eYoung
 * @description:
 * @date create at 2021/12/3 16:34
 */
public class EventBuilder extends BaseBuilder<EventBuilder, WxCpXmlOutEventMessage> {

  private String event;
  private String chatId;
  private String changeType;
  private String updateDetail;
  private String joinScene;
  private String quitScene;
  private String memChangeCnt;
  private String tagType;
  private String strategyId;
  private String userID;
  private String externalUserID;
  private String state;
  private String welcomeCode;
  private String source;
  private String failReason;
  private String id;

  public EventBuilder chatId(String chatId) {
    this.chatId = chatId;
    return this;
  }

  public EventBuilder event(String event) {
    this.event = event;
    return this;
  }

  public EventBuilder changeType(String changeType) {
    this.changeType = changeType;
    return this;
  }

  public EventBuilder updateDetail(String updateDetail) {
    this.updateDetail = updateDetail;
    return this;
  }

  public EventBuilder joinScene(String joinScene) {
    this.joinScene = joinScene;
    return this;
  }

  public EventBuilder quitScene(String quitScene) {
    this.quitScene = quitScene;
    return this;
  }

  public EventBuilder memChangeCnt(String memChangeCnt) {
    this.memChangeCnt = memChangeCnt;
    return this;
  }

  public EventBuilder tagType(String tagType) {
    this.tagType = tagType;
    return this;
  }

  public EventBuilder strategyId(String strategyId) {
    this.strategyId = strategyId;
    return this;
  }

  public EventBuilder userID(String userID) {
    this.userID = userID;
    return this;
  }

  public EventBuilder externalUserID(String externalUserID) {
    this.externalUserID = externalUserID;
    return this;
  }

  public EventBuilder state(String state) {
    this.state = state;
    return this;
  }

  public EventBuilder source(String source) {
    this.source = source;
    return this;
  }

  public EventBuilder welcomeCode(String welcomeCode) {
    this.welcomeCode = welcomeCode;
    return this;
  }

  public EventBuilder failReason(String failReason) {
    this.failReason = failReason;
    return this;
  }

  public EventBuilder id(String id) {
    this.id = id;
    return this;
  }

  @Override
  public WxCpXmlOutEventMessage build() {
    WxCpXmlOutEventMessage m = new WxCpXmlOutEventMessage();
    super.setCommon(m);
    m.setEvent(this.event);
    m.setChatId(this.chatId);
    m.setChangeType(this.changeType);
    m.setUpdateDetail(this.updateDetail);
    m.setJoinScene(this.joinScene);
    m.setQuitScene(this.quitScene);
    m.setMemChangeCnt(this.memChangeCnt);
    m.setTagType(this.tagType);
    m.setStrategyId(this.strategyId);
    m.setUserID(this.userID);
    m.setExternalUserID(this.externalUserID);
    m.setState(this.state);
    m.setWelcomeCode(this.welcomeCode);
    m.setSource(this.source);
    m.setFailReason(this.failReason);
    m.setId(this.id);
    return m;
  }
}
