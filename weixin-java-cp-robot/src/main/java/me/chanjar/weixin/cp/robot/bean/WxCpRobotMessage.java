package me.chanjar.weixin.cp.robot.bean;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.robot.bean.messagebuilder.RobotImageBuilder;
import me.chanjar.weixin.cp.robot.bean.messagebuilder.RobotMarkdownBuilder;
import me.chanjar.weixin.cp.robot.bean.messagebuilder.RobotNewsBuilder;
import me.chanjar.weixin.cp.robot.bean.messagebuilder.RobotTextBuilder;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static me.chanjar.weixin.common.api.WxConsts.KefuMsgType.*;

/**
 * 群机器人消息
 *
 * @author linfeng-eqxiu
 * @date 2019/12/3
 */
@Data
public class WxCpRobotMessage implements Serializable {

  private static final long serialVersionUID = 2847975834795112163L;
  /**
   * 机器人发送消息的Key
   */
  private String key;
  /**
   * 消息类型
   */
  private String msgType;

  /**
   * 富文本框里面的内容
   */
  private String content;
  /**
   * 涉及的人员
   */
  private List<String> memberList = null;
  /**
   * 电话
   */
  private List<String> mobileList = null;
  /**
   * 图片地址
   */
  private String imageUrl;

  /**
   * base64编码后的值
   */
  private String imageBase64Value;

  /**
   * 图片md5加密后的值
   */
  private String imageMd5Value;

  /**
   * 发送消息的标题
   */
  private String title;
  /**
   * 发送图文消息的描述信息
   */
  private String description;
  /**
   * 图片url地址集合
   */
  private List<String> imageUrlList;
  /**
   * 图片打开的地址
   */
  private String url;
  /**
   * 消息内容集合
   */
  private List<String> contentList;
  /**
   * 图片路径
   */
  private String savePath;
  /**
   *
   */
  private List<NewArticle> articles = new ArrayList<>();

  /**
   * 获得文本消息builder.
   */
  public static RobotTextBuilder TEXT() {
    return new RobotTextBuilder();
  }

  /**
   * 获得图片消息builder.
   */
  public static RobotImageBuilder IMAGE() {
    return new RobotImageBuilder();
  }

  /**
   * 获得markdown消息builder.
   */
  public static RobotMarkdownBuilder MARKDOWN() {
    return new RobotMarkdownBuilder();
  }

  /**
   * 获得图文消息builder.
   */
  public static RobotNewsBuilder NEWS() {
    return new RobotNewsBuilder();
  }

  /**
   * <pre>
   * 请使用.
   * {@link WxConsts.KefuMsgType#TEXT}
   * {@link WxConsts.KefuMsgType#IMAGE}
   * {@link WxConsts.KefuMsgType#NEWS}
   * {@link WxConsts.KefuMsgType#MARKDOWN}
   * </pre>
   *
   * @param msgType 消息类型
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String toJson() {
    JsonObject messageJson = new JsonObject();

    messageJson.addProperty("msgtype", this.getMsgType());

    this.handleMsgType(messageJson);

    return messageJson.toString();
  }

  /**
   * @param messageJson
   */
  private void handleMsgType(JsonObject messageJson) {
    switch (this.getMsgType()) {
      case TEXT: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        setMessageTarget(text);
        messageJson.add("text", text);
        break;
      }
      case MARKDOWN: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        setMessageTarget(text);
        messageJson.add("markdown", text);
        break;
      }
      case IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("base64", this.getImageBase64Value());
        image.addProperty("md5", this.getImageMd5Value());
        setMessageTarget(image);
        messageJson.add("image", image);
        break;
      }
      case NEWS: {
        JsonObject newsJsonObject = new JsonObject();
        JsonArray articleJsonArray = new JsonArray();
        for (NewArticle article : this.getArticles()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("title", article.getTitle());
          articleJson.addProperty("description", article.getDescription());
          articleJson.addProperty("url", article.getUrl());
          articleJson.addProperty("picurl", article.getPicUrl());
          articleJsonArray.add(articleJson);
        }
        newsJsonObject.add("articles", articleJsonArray);
        setMessageTarget(newsJsonObject);
        messageJson.add("news", newsJsonObject);
        break;
      }
      default: {
        // do nothing
      }
    }
  }

  /**
   * 设置消息发送的目标
   *
   * @param messageJson 待追加消息发送目标的对象
   */
  private void setMessageTarget(JsonObject messageJson) {
    // 用户ID集合不为空
    if (this.getMemberList() != null && !this.getMemberList().isEmpty()) {
      JsonElement mentionedString = WxCpGsonBuilder.create().toJsonTree(this.getMemberList());
      messageJson.add("mentioned_list", mentionedString);
    }
    // 用户手机号集合不为空
    if (this.getMobileList() != null && !this.getMobileList().isEmpty()) {
      JsonElement mobileString = WxCpGsonBuilder.create().toJsonTree(this.getMobileList());
      messageJson.add("mentioned_mobile_list", mobileString);
    }
  }
}
