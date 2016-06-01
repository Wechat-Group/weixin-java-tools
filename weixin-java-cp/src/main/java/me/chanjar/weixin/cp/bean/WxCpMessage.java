package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.cp.bean.messagebuilder.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 * @author Daniel Qian
 *
 */
public class WxCpMessage implements Serializable {

  private String toUser;
  private String toParty;
  private String toTag;
  private String agentId;
  private String msgType;
  private String content;
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;
  private String musicUrl;
  private String hqMusicUrl;
  private String safe;
  private List<WxArticle> articles = new ArrayList<WxArticle>();
  
  public String getToUser() {
    return toUser;
  }
  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public String getToParty() {
    return toParty;
  }

  public void setToParty(String toParty) {
    this.toParty = toParty;
  }

  public String getToTag() {
    return toTag;
  }

  public void setToTag(String toTag) {
    this.toTag = toTag;
  }

  public String getAgentId() {
    return agentId;
  }

  public void setAgentId(String agentId) {
    this.agentId = agentId;
  }

  public String getMsgType() {
    return msgType;
  }
  
  public String getSafe() {
    return safe;
  }

  public void setSafe(String safe) {
    this.safe = safe;
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
   * @param msgType
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getMediaId() {
    return mediaId;
  }
  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }
  public String getThumbMediaId() {
    return thumbMediaId;
  }
  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getMusicUrl() {
    return musicUrl;
  }
  public void setMusicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
  }
  public String getHqMusicUrl() {
    return hqMusicUrl;
  }
  public void setHqMusicUrl(String hqMusicUrl) {
    this.hqMusicUrl = hqMusicUrl;
  }
  public List<WxArticle> getArticles() {
    return articles;
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
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }
    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }
    public String getUrl() {
      return url;
    }
    public void setUrl(String url) {
      this.url = url;
    }
    public String getPicUrl() {
      return picUrl;
    }
    public void setPicUrl(String picUrl) {
      this.picUrl = picUrl;
    }
    
  }
  
  /**
   * 获得文本消息builder
   * @return
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder
   * @return
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder
   * @return
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }
  
  /**
   * 获得视频消息builder
   * @return
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }
  
  /**
   * 获得图文消息builder
   * @return
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得文件消息builder
   * @return
   */
  public static FileBuilder FILE() {
    return new FileBuilder();
  }
  
}
