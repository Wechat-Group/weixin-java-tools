package me.chanjar.weixin.mp.bean;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 按标签群发的消息
 *
 * @author chanjarster
 */
@Data
public class WxMpMassTagMessage implements Serializable {
  private static final long serialVersionUID = -6625914040986749286L;

  /**
   * 标签id，如果不设置则就意味着发给所有用户
   */
  private Long tagId;
  /**
   * <pre>
   * 消息类型
   * 请使用
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_IMAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_NEWS}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_TEXT}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_VIDEO}
   * {@link me.chanjar.weixin.common.api.WxConsts#MASS_MSG_VOICE}
   * 如果msgtype和media_id不匹配的话，会返回系统繁忙的错误
   * </pre>
   */
  private String msgType;
  private String content;
  private String mediaId;
  /**
   * 是否群发给所有用户
   */
  private boolean isSendAll = false;
  /**
   * 文章被判定为转载时，是否继续进行群发操作。
   */
  private boolean sendIgnoreReprint = false;

  public WxMpMassTagMessage() {
    super();
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public void setSendAll(boolean sendAll) {
    if (sendAll) {
      this.tagId = null;
    }

    isSendAll = sendAll;
  }
}
