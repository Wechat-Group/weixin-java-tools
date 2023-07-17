package me.chanjar.weixin.cp.bean.outxmlbuilder;

import me.chanjar.weixin.cp.bean.message.WxCpXmlOutEventMessage;

/**
 * The type Event builder.
 *
 * @author eYoung
 * created at 2021/12/3 16:34
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

  /**
   * Chat id event builder.
   *
   * @param chatId the chat id
   * @return the event builder
   */
  public EventBuilder chatId(String chatId) {
    this.chatId = chatId;
    return this;
  }

  /**
   * Event event builder.
   *
   * @param event the event
   * @return the event builder
   */
  public EventBuilder event(String event) {
    this.event = event;
    return this;
  }

  /**
   * Change type event builder.
   *
   * @param changeType the change type
   * @return the event builder
   */
  public EventBuilder changeType(String changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Update detail event builder.
   *
   * @param updateDetail the update detail
   * @return the event builder
   */
  public EventBuilder updateDetail(String updateDetail) {
    this.updateDetail = updateDetail;
    return this;
  }

  /**
   * Join scene event builder.
   *
   * @param joinScene the join scene
   * @return the event builder
   */
  public EventBuilder joinScene(String joinScene) {
    this.joinScene = joinScene;
    return this;
  }

  /**
   * Quit scene event builder.
   *
   * @param quitScene the quit scene
   * @return the event builder
   */
  public EventBuilder quitScene(String quitScene) {
    this.quitScene = quitScene;
    return this;
  }

  /**
   * Mem change cnt event builder.
   *
   * @param memChangeCnt the mem change cnt
   * @return the event builder
   */
  public EventBuilder memChangeCnt(String memChangeCnt) {
    this.memChangeCnt = memChangeCnt;
    return this;
  }

  /**
   * Tag type event builder.
   *
   * @param tagType the tag type
   * @return the event builder
   */
  public EventBuilder tagType(String tagType) {
    this.tagType = tagType;
    return this;
  }

  /**
   * Strategy id event builder.
   *
   * @param strategyId the strategy id
   * @return the event builder
   */
  public EventBuilder strategyId(String strategyId) {
    this.strategyId = strategyId;
    return this;
  }

  /**
   * User id event builder.
   *
   * @param userID the user id
   * @return the event builder
   */
  public EventBuilder userID(String userID) {
    this.userID = userID;
    return this;
  }

  /**
   * External user id event builder.
   *
   * @param externalUserID the external user id
   * @return the event builder
   */
  public EventBuilder externalUserID(String externalUserID) {
    this.externalUserID = externalUserID;
    return this;
  }

  /**
   * State event builder.
   *
   * @param state the state
   * @return the event builder
   */
  public EventBuilder state(String state) {
    this.state = state;
    return this;
  }

  /**
   * Source event builder.
   *
   * @param source the source
   * @return the event builder
   */
  public EventBuilder source(String source) {
    this.source = source;
    return this;
  }

  /**
   * Welcome code event builder.
   *
   * @param welcomeCode the welcome code
   * @return the event builder
   */
  public EventBuilder welcomeCode(String welcomeCode) {
    this.welcomeCode = welcomeCode;
    return this;
  }

  /**
   * Fail reason event builder.
   *
   * @param failReason the fail reason
   * @return the event builder
   */
  public EventBuilder failReason(String failReason) {
    this.failReason = failReason;
    return this;
  }

  /**
   * Id event builder.
   *
   * @param id the id
   * @return the event builder
   */
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
