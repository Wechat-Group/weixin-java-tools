package me.chanjar.weixin.cp.bean.outxmlbuilder;

import me.chanjar.weixin.cp.bean.message.WxCpXmlOutImageMessage;

/**
 * 图片消息builder
 *
 * @author Daniel Qian
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder, WxCpXmlOutImageMessage> {

  private String mediaId;

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
  public WxCpXmlOutImageMessage build() {
    WxCpXmlOutImageMessage m = new WxCpXmlOutImageMessage();
    setCommon(m);
    m.setMediaId(this.mediaId);
    return m;
  }

}
