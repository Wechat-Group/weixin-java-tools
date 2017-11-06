package me.chanjar.weixin.mp.bean;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author miller
 */
@Data
public class WxMpMassPreviewMessage implements Serializable {
  private static final long serialVersionUID = 9095211638358424020L;

  private String toWxUserName;
  private String toWxUserOpenid;
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

  public WxMpMassPreviewMessage() {
    super();
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }
}
