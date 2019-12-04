package me.chanjar.weixin.cp.robot.bean.messagebuilder;

import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;

/**
 * @author linfeng-eqxiu
 * @date 2019/12/3
 */
public class RobotBuilder<T> {

  protected String key;
  protected String msgType;

  public T key(String key) {
    this.key = key;
    return (T) this;
  }

  public WxCpRobotMessage build() {
    WxCpRobotMessage m = new WxCpRobotMessage();
    m.setKey(this.key);
    m.setMsgType(this.msgType);
    return m;
  }

}
