package me.chanjar.weixin.mp.bean;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 按标签群发的消息
 *
 * @author chanjarster
 */
public class WxMpMassTagMessage implements Serializable {

  private static final long serialVersionUID = -6625914040986749286L;
  private Long tagId;
  private String msgType;
  private String content;
  private String mediaId;
  private boolean isSendAll = false;
  private boolean sendIgnoreReprint = false;

  public WxMpMassTagMessage() {
    super();
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

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public Long getTagId() {
    return this.tagId;
  }

  /**
   * 如果不设置则就意味着发给所有用户
   *
   * @param tagId 标签id
   */
  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  public boolean isSendIgnoreReprint() {
    return sendIgnoreReprint;
  }

  /**
   *
   * @param sendIgnoreReprint 文章被判定为转载时，是否继续进行群发操作。
   */
  public void setSendIgnoreReprint(boolean sendIgnoreReprint) {
    this.sendIgnoreReprint = sendIgnoreReprint;
  }

  /**
   * 是否群发给所有用户
   */
  public boolean isSendAll() {
    return isSendAll;
  }

  public void setSendAll(boolean sendAll) {
    if(sendAll){
      this.tagId = null;
    }

    isSendAll = sendAll;
  }
}
