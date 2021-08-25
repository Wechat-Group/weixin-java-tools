package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The type Wx cp news message.
 *
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpNewsMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("news")
  private NewsMessageWrap newsMessageWrap;

  /**
   * Builder wx cp news message builder.
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
   * @return the wx cp news message builder
   */
  public static WxCpNewsMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  /**
   * Builder to user wx cp news message builder.
   *
   * @param toUser 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @return the wx cp news message builder
   */
  public static WxCpNewsMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  /**
   * 发送给所有人
   *
   * @return the wx cp news message builder
   */
  public static WxCpNewsMessageBuilder builderToAll() {
    return _builder_().toUser(ALL);
  }

  /**
   * 发送给部门
   *
   * @param toParty 指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                当touser为”@all”时忽略本参数
   * @return the wx cp news message builder
   */
  public static WxCpNewsMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  /**
   * 发送给标签
   *
   * @param toTag 指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *              当touser为”@all”时忽略本参数
   * @return the wx cp news message builder
   */
  public static WxCpNewsMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpNewsMessageBuilder _builder_() {
    return new WxCpNewsMessageBuilder();
  }

  /**
   * Instantiates a new Wx cp news message.
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
   * @param newsMessages           the news messages
   */
  @Builder(builderMethodName = "_builder_")
  public WxCpNewsMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, NewsMessage... newsMessages) {
    setMsgType("news");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.newsMessageWrap = new NewsMessageWrap(newsMessages);
  }

  /**
   * The type News message wrap.
   */
  @Data
  public static class NewsMessageWrap implements Serializable {

    private static final long serialVersionUID = -211238111370772820L;

    @SerializedName("articles")
    private List<NewsMessage> newsMessageList;

    /**
     * Instantiates a new News message wrap.
     *
     * @param newsMessageList the news message list
     */
    public NewsMessageWrap(NewsMessage... newsMessageList) {
      if (newsMessageList != null) {
        this.newsMessageList = Arrays.asList(newsMessageList);
      }
    }
  }

  /**
   * The type News message.
   */
  @Data
  public static class NewsMessage implements Serializable {

    private static final long serialVersionUID = -6288061856098371839L;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("picurl")
    private String picUrl;

    @SerializedName("appid")
    private String appId;

    @SerializedName("pagepath")
    private String pagePath;

    /**
     * Instantiates a new News message.
     *
     * @param title       the title
     * @param description the description
     * @param url         the url
     * @param picUrl      the pic url
     * @param appId       the app id
     * @param pagePath    the page path
     */
    public NewsMessage(String title, String description, String url, String picUrl, String appId, String pagePath) {
      this.title = title;
      this.description = description;
      this.url = url;
      this.picUrl = picUrl;
      this.appId = appId;
      this.pagePath = pagePath;
    }
  }
}
