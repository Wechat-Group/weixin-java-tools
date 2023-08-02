package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * 获得消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.IMAGE().mediaId(...).toUser(...).build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder> {
  private String mediaId;

  /**
   * Instantiates a new Image builder.
   */
  public ImageBuilder() {
    this.msgType = WxConsts.KefuMsgType.IMAGE;
  }

  /**
   * Media id image builder.
   *
   * @param media_id the media id
   * @return the image builder
   */
  public ImageBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
