package me.chanjar.weixin.cp.bean.outxmlbuilder;

import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 * The type Base builder.
 *
 * @param <BuilderType> the type parameter
 * @param <ValueType>   the type parameter
 */
public abstract class BaseBuilder<BuilderType, ValueType> {

  /**
   * The To user name.
   */
  protected String toUserName;

  /**
   * The From user name.
   */
  protected String fromUserName;

  /**
   * To user builder type.
   *
   * @param touser the touser
   * @return the builder type
   */
  public BuilderType toUser(String touser) {
    this.toUserName = touser;
    return (BuilderType) this;
  }

  /**
   * From user builder type.
   *
   * @param fromusername the fromusername
   * @return the builder type
   */
  public BuilderType fromUser(String fromusername) {
    this.fromUserName = fromusername;
    return (BuilderType) this;
  }

  /**
   * Build value type.
   *
   * @return the value type
   */
  public abstract ValueType build();

  /**
   * Sets common.
   *
   * @param m the m
   */
  public void setCommon(WxCpXmlOutMessage m) {
    m.setToUserName(this.toUserName);
    m.setFromUserName(this.fromUserName);
    m.setCreateTime(System.currentTimeMillis() / 1000L);
  }

}
