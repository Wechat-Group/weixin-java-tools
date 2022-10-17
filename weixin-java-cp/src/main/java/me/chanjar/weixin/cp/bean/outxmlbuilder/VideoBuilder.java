package me.chanjar.weixin.cp.bean.outxmlbuilder;

import me.chanjar.weixin.cp.bean.message.WxCpXmlOutVideoMessage;

/**
 * 视频消息builder
 *
 * @author Daniel Qian
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder, WxCpXmlOutVideoMessage> {

  private String mediaId;
  private String title;
  private String description;

  /**
   * Title video builder.
   *
   * @param title the title
   * @return the video builder
   */
  public VideoBuilder title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Description video builder.
   *
   * @param description the description
   * @return the video builder
   */
  public VideoBuilder description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Media id video builder.
   *
   * @param mediaId the media id
   * @return the video builder
   */
  public VideoBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxCpXmlOutVideoMessage build() {
    WxCpXmlOutVideoMessage m = new WxCpXmlOutVideoMessage();
    setCommon(m);
    m.setTitle(this.title);
    m.setDescription(this.description);
    m.setMediaId(this.mediaId);
    return m;
  }

}
