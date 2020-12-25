package me.chanjar.weixin.qidian.builder.outxml;

import me.chanjar.weixin.qidian.bean.message.WxMpXmlOutVideoMessage;

/**
 * 视频消息builder
 *
 * @author chanjarster
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder, WxMpXmlOutVideoMessage> {

  private String mediaId;
  private String title;
  private String description;

  public VideoBuilder title(String title) {
    this.title = title;
    return this;
  }

  public VideoBuilder description(String description) {
    this.description = description;
    return this;
  }

  public VideoBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxMpXmlOutVideoMessage build() {
    WxMpXmlOutVideoMessage m = new WxMpXmlOutVideoMessage();
    setCommon(m);
    m.getVideo().setTitle(this.title);
    m.getVideo().setDescription(this.description);
    m.getVideo().setMediaId(this.mediaId);
    return m;
  }

}
