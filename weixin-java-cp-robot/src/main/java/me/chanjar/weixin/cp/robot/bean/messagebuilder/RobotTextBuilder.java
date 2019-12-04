package me.chanjar.weixin.cp.robot.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 机器人文本消息builder
 * <pre>
 * 用法: WxCpRobotMessage m = WxCpRobotMessage.TEXT().content(...).addMember(...).build();
 * </pre>
 *
 * @author linfeng
 */
public final class RobotTextBuilder extends RobotBuilder<RobotTextBuilder> {
  private String content;
  protected List<String> toMember = new ArrayList<>();
  protected List<String> toMobile = new ArrayList<>();

  public RobotTextBuilder() {
    this.msgType = WxConsts.KefuMsgType.TEXT;
  }

  public RobotTextBuilder addMember(String member) {
    this.toMember.add(member);
    return this;
  }

  public RobotTextBuilder addMobile(String mobile) {
    this.toMobile.add(mobile);
    return this;
  }

  public RobotTextBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxCpRobotMessage build() {
    WxCpRobotMessage m = super.build();
    m.setContent(this.content);
    m.setMemberList(this.toMember);
    m.setMobileList(this.toMobile);
    return m;
  }
}
