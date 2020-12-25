package me.chanjar.weixin.qidian.builder.outxml;

import me.chanjar.weixin.qidian.bean.message.WxMpXmlOutMusicMessage;

/**
 * 音乐消息builder
 *
 * @author chanjarster
 */
public final class MusicBuilder extends BaseBuilder<MusicBuilder, WxMpXmlOutMusicMessage> {

  private String title;
  private String description;
  private String hqMusicUrl;
  private String musicUrl;
  private String thumbMediaId;

  public MusicBuilder title(String title) {
    this.title = title;
    return this;
  }

  public MusicBuilder description(String description) {
    this.description = description;
    return this;
  }

  public MusicBuilder hqMusicUrl(String hqMusicUrl) {
    this.hqMusicUrl = hqMusicUrl;
    return this;
  }

  public MusicBuilder musicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
    return this;
  }

  public MusicBuilder thumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
    return this;
  }

  @Override
  public WxMpXmlOutMusicMessage build() {
    WxMpXmlOutMusicMessage m = new WxMpXmlOutMusicMessage();
    setCommon(m);
    m.setTitle(this.title);
    m.setDescription(this.description);
    m.setHqMusicUrl(this.hqMusicUrl);
    m.setMusicUrl(this.musicUrl);
    m.setThumbMediaId(this.thumbMediaId);
    return m;
  }

}
