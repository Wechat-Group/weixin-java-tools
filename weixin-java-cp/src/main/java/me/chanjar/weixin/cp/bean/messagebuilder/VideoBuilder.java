package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * 视频消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.VOICE()
 *                              .mediaId(...)
 *                              .title(...)
 *                              .thumbMediaId(..)
 *                              .description(..)
 *                              .toUser(...)
 *                              .build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder> {
  private String mediaId;
  private String title;
  private String description;
  private String thumbMediaId;

  /**
   * Instantiates a new Video builder.
   */
  public VideoBuilder() {
    this.msgType = WxConsts.KefuMsgType.VIDEO;
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
   * Thumb media id video builder.
   *
   * @param thumb_media_id the thumb media id
   * @return the video builder
   */
  public VideoBuilder thumbMediaId(String thumb_media_id) {
    this.thumbMediaId = thumb_media_id;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setMediaId(this.mediaId);
    m.setTitle(this.title);
    m.setDescription(this.description);
    m.setThumbMediaId(this.thumbMediaId);
    return m;
  }
}
