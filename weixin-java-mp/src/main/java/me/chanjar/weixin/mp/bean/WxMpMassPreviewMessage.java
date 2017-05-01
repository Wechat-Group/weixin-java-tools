package me.chanjar.weixin.mp.bean;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author miller
 */
public class WxMpMassPreviewMessage implements Serializable {
  private static final long serialVersionUID = 9095211638358424020L;
  private String toWxUserName;
  private String toWxUserOpenid;
  private String msgType;
  private String content;
  private String mediaId;

  public WxMpMassPreviewMessage() {
    super();
  }

  public String getToWxUserName() {
    return this.toWxUserName;
  }

  public void setToWxUserName(String toWxUserName) {
    this.toWxUserName = toWxUserName;
  }

  public String getMsgType() {
    return this.msgType;
  }

  /**
   * <pre>
   * 请使用
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_IMAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_NEWS}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_TEXT}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_VIDEO}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_VOICE}
   * 如果msgtype和media_id不匹配的话，会返回系统繁忙的错误
   * </pre>
   *
   * @param msgType 消息类型
   */
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

  public String getToWxUserOpenid() {
    return this.toWxUserOpenid;
  }

  public void setToWxUserOpenid(String toWxUserOpenid) {
    this.toWxUserOpenid = toWxUserOpenid;
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }
}
