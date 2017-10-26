package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.builder.ImageBuilder;
import cn.binarywang.wx.miniapp.builder.TextBuilder;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;

import java.io.Serializable;

/**
 * 客服消息
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaKefuMessage implements Serializable {
  private static final long serialVersionUID = -9196732086954365246L;

  private String toUser;
  private String msgType;
  private String content;
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;

  /**
   * 获得文本消息builder
   */
  public static TextBuilder newTextBuilder() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder
   */
  public static ImageBuilder newImageBuilder() {
    return new ImageBuilder();
  }

  public String getToUser() {
    return this.toUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public String getMsgType() {
    return this.msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getMediaId() {
    return this.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public String getThumbMediaId() {
    return this.thumbMediaId;
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}
