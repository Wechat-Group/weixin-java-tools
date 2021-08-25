package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * The type Wx cp markdown message.
 *
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpMarkdownMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("markdown")
  private MarkdownMessage markdownMessage;

  /**
   * Builder wx cp markdown message builder.
   *
   * @param toUser                 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *                               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @param toParty                指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param toTag                  指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param agentId                企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
   * @param safe                   是否是保密消息，0表示可对外分享，1表示不能分享且内容显示水印，默认为0
   * @param enableIdTrans          是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
   * @param enableDuplicateCheck   是否开启重复消息检查，0表示否，1表示是，默认0
   * @param duplicateCheckInterval 是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
   * @return the wx cp markdown message builder
   */
  public static WxCpMarkdownMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  /**
   * Builder to user wx cp markdown message builder.
   *
   * @param toUser 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @return the wx cp markdown message builder
   */
  public static WxCpMarkdownMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  /**
   * 发送给所有人
   *
   * @return the wx cp markdown message builder
   */
  public static WxCpMarkdownMessageBuilder builderToAll() {
    return _builder_().toUser(ALL);
  }

  /**
   * 发送给部门
   *
   * @param toParty 指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                当touser为”@all”时忽略本参数
   * @return the wx cp markdown message builder
   */
  public static WxCpMarkdownMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  /**
   * 发送给标签
   *
   * @param toTag 指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *              当touser为”@all”时忽略本参数
   * @return the wx cp markdown message builder
   */
  public static WxCpMarkdownMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpMarkdownMessageBuilder _builder_() {
    return new WxCpMarkdownMessageBuilder();
  }

  /**
   * Instantiates a new Wx cp markdown message.
   *
   * @param toUser                 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *                               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @param toParty                指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param toTag                  指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param agentId                企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
   * @param safe                   是否是保密消息，0表示可对外分享，1表示不能分享且内容显示水印，默认为0
   * @param enableIdTrans          是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
   * @param enableDuplicateCheck   是否开启重复消息检查，0表示否，1表示是，默认0
   * @param duplicateCheckInterval 是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
   * @param content                the content
   */
  @Builder(builderMethodName = "_builder_")
  public WxCpMarkdownMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String content) {
    setMsgType("markdown");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.markdownMessage = new MarkdownMessage(content);
  }

  /**
   * The type Markdown message.
   */
  @Data
  public static class MarkdownMessage {

    @SerializedName("content")
    private String content;

    /**
     * Instantiates a new Markdown message.
     *
     * @param content the content
     */
    public MarkdownMessage(String content) {
      this.content = content;
    }
  }
}
