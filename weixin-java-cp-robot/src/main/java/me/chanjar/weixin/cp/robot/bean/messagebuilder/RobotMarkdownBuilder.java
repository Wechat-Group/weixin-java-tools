package me.chanjar.weixin.cp.robot.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;

/**
 * 机器人Markdown消息builder
 * <pre>
 * 用法: WxCpRobotMessage m = WxCpRobotMessage.TEXT().content(...).addMember(...).build();
 * </pre>
 *
 * @author linfeng
 */
public final class RobotMarkdownBuilder extends RobotBuilder<RobotMarkdownBuilder> {
  private String content;

  public RobotMarkdownBuilder() {
    this.msgType = WxConsts.KefuMsgType.MARKDOWN;
  }

  public RobotMarkdownBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxCpRobotMessage build() {
    WxCpRobotMessage m = super.build();
    m.setContent(this.content);
    return m;
  }
}
