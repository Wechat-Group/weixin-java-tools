package me.chanjar.weixin.qidian.builder.outxml;

import me.chanjar.weixin.qidian.bean.message.WxMpXmlOutMessage;

public abstract class BaseBuilder<BuilderType, ValueType> {

  protected String toUserName;

  protected String fromUserName;

  @SuppressWarnings("unchecked")
  public BuilderType toUser(String touser) {
    this.toUserName = touser;
    return (BuilderType) this;
  }

  @SuppressWarnings("unchecked")
  public BuilderType fromUser(String fromusername) {
    this.fromUserName = fromusername;
    return (BuilderType) this;
  }

  public abstract ValueType build();

  public void setCommon(WxMpXmlOutMessage m) {
    m.setToUserName(this.toUserName);
    m.setFromUserName(this.fromUserName);
    m.setCreateTime(System.currentTimeMillis() / 1000L);
  }

}
