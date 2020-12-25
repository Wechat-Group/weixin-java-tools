package me.chanjar.weixin.qidian.builder.kefu;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.qidian.bean.kefu.WxMpKefuMessage;

/**
 * 语音消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpKefuMessage.VOICE().mediaId(...).toUser(...).build();
 * </pre>
 *
 * @author chanjarster
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder> {
  private String mediaId;

  public VoiceBuilder() {
    this.msgType = WxConsts.KefuMsgType.VOICE;
  }

  public VoiceBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
