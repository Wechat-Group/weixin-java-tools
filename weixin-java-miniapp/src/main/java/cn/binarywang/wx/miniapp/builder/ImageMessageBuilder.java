package cn.binarywang.wx.miniapp.builder;

import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;

import static cn.binarywang.wx.miniapp.constant.WxMaConstants.KefuMsgType;

/**
 * 图片消息builder.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public final class ImageMessageBuilder extends BaseBuilder<ImageMessageBuilder> {
  private String mediaId;

  public ImageMessageBuilder() {
    this.msgType = KefuMsgType.IMAGE;
  }

  public ImageMessageBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxMaKefuMessage build() {
    WxMaKefuMessage m = super.build();
    m.setImage(new WxMaKefuMessage.KfImage(this.mediaId));
    return m;
  }
}
