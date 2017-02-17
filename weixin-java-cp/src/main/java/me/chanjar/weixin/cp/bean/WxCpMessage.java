package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.cp.bean.messagebuilder.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 *
 * @author Daniel Qian
 */
public class WxCpMessage implements Serializable {

  private static final long serialVersionUID = -2082278303476631708L;
  private String toUser;
  private String toParty;
  private String toTag;
  private Integer agentId;
  private String msgType;
  private String content;
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;
  private String musicUrl;
  private String hqMusicUrl;
  private String safe;
  private List<WxArticle> articles = new ArrayList<>();

  /**
   * 获得文本消息builder
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得图文消息builder
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得文件消息builder
   */
  public static FileBuilder FILE() {
    return new FileBuilder();
  }

  public String getToUser() {
    return this.toUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public String getToParty() {
    return this.toParty;
  }

  public void setToParty(String toParty) {
    this.toParty = toParty;
  }

  public String getToTag() {
    return this.toTag;
  }

  public void setToTag(String toTag) {
    this.toTag = toTag;
  }

  public Integer getAgentId() {
    return this.agentId;
  }

  public void setAgentId(Integer agentId) {
    this.agentId = agentId;
  }

  public String getMsgType() {
    return this.msgType;
  }

  /**
   * <pre>
   * 请使用
   * {@link me.chanjar.weixin.common.api.WxConsts#CUSTOM_MSG_TEXT}
   * {@link me.chanjar.weixin.common.api.WxConsts#CUSTOM_MSG_IMAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts#CUSTOM_MSG_VOICE}
   * {@link me.chanjar.weixin.common.api.WxConsts#CUSTOM_MSG_MUSIC}
   * {@link me.chanjar.weixin.common.api.WxConsts#CUSTOM_MSG_VIDEO}
   * {@link me.chanjar.weixin.common.api.WxConsts#CUSTOM_MSG_NEWS}
   * </pre>
   *
   * @param msgType
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getSafe() {
    return this.safe;
  }

  public void setSafe(String safe) {
    this.safe = safe;
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

  public String getMusicUrl() {
    return this.musicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
  }

  public String getHqMusicUrl() {
    return this.hqMusicUrl;
  }

  public void setHqMusicUrl(String hqMusicUrl) {
    this.hqMusicUrl = hqMusicUrl;
  }

  public List<WxArticle> getArticles() {
    return this.articles;
  }

  public void setArticles(List<WxArticle> articles) {
    this.articles = articles;
  }

  public String toJson() {
    return WxCpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public static class WxArticle {

    private String title;
    private String description;
    private String url;
    private String picUrl;

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

    public String getUrl() {
      return this.url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getPicUrl() {
      return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
      this.picUrl = picUrl;
    }

  }

}
