package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.taskcard.TaskCardButton;

import java.util.List;

/**
 * <pre>
 * 任务卡片消息Builder
 * 用法: WxCustomMessage m = WxCustomMessage.TASKCARD().title(...)....toUser(...).build();
 * </pre>
 *
 * @author <a href="https://github.com/domainname">Jeff</a>
 * @date 2019-05-16
 */
public class TaskCardBuilder extends BaseBuilder<TaskCardBuilder> {
  private String title;
  private String description;
  private String url;
  private String taskId;
  /**
   * 按钮个数为1~2个
   */
  private List<TaskCardButton> buttons;

  public TaskCardBuilder() {
    this.msgType = WxConsts.KefuMsgType.TASKCARD;
  }

  public TaskCardBuilder title(String title) {
    this.title = title;
    return this;
  }

  public TaskCardBuilder description(String description) {
    this.description = description;
    return this;
  }

  public TaskCardBuilder url(String url) {
    this.url = url;
    return this;
  }

  public TaskCardBuilder taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  public TaskCardBuilder buttons(List<TaskCardButton> buttons) {
    this.buttons = buttons;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setSafe(null);
    m.setTitle(this.title);
    m.setDescription(this.description);
    m.setUrl(this.url);
    m.setTaskId(this.taskId);
    m.setTaskButtons(this.buttons);
    return m;
  }
}
