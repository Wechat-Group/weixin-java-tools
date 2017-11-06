package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutMusicMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -4159937804975448945L;

  @XStreamAlias("Music")
  protected final Music music = new Music();

  public WxMpXmlOutMusicMessage() {
    this.msgType = WxConsts.XML_MSG_MUSIC;
  }

  @XStreamAlias("Music")
  @Data
  public static class Music implements Serializable {
    private static final long serialVersionUID = -5492592401691895334L;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

    @XStreamAlias("ThumbMediaId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String thumbMediaId;

    @XStreamAlias("MusicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String musicUrl;

    @XStreamAlias("HQMusicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String hqMusicUrl;
  }

  public String getTitle() {
    return this.music.title;
  }

  public void setTitle(String title) {
    this.music.title = title;
  }

  public String getDescription() {
    return this.music.description;
  }

  public void setDescription(String description) {
    this.music.description = description;
  }

  public String getThumbMediaId() {
    return this.music.thumbMediaId;
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.music.thumbMediaId = thumbMediaId;
  }

  public String getMusicUrl() {
    return this.music.musicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.music.musicUrl = musicUrl;
  }

  public String getHqMusicUrl() {
    return this.music.hqMusicUrl;
  }

  public void setHqMusicUrl(String hqMusicUrl) {
    this.music.hqMusicUrl = hqMusicUrl;
  }

}
