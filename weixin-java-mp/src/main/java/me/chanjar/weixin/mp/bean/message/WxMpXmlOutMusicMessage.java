package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

@XStreamAlias("xml")
public class WxMpXmlOutMusicMessage extends WxMpXmlOutMessage {

  /**
   *
   */
  private static final long serialVersionUID = -4159937804975448945L;
  @XStreamAlias("Music")
  protected final Music music = new Music();

  public WxMpXmlOutMusicMessage() {
    this.msgType = WxConsts.XML_MSG_MUSIC;
  }

  public String getTitle() {
    return this.music.getTitle();
  }

  public void setTitle(String title) {
    this.music.setTitle(title);
  }

  public String getDescription() {
    return this.music.getDescription();
  }

  public void setDescription(String description) {
    this.music.setDescription(description);
  }

  public String getThumbMediaId() {
    return this.music.getThumbMediaId();
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.music.setThumbMediaId(thumbMediaId);
  }

  public String getMusicUrl() {
    return this.music.getMusicUrl();
  }

  public void setMusicUrl(String musicUrl) {
    this.music.setMusicUrl(musicUrl);
  }

  public String getHqMusicUrl() {
    return this.music.getHqMusicUrl();
  }

  public void setHqMusicUrl(String hqMusicUrl) {
    this.music.setHqMusicUrl(hqMusicUrl);
  }

  @XStreamAlias("Music")
  public static class Music {

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

    public String getTitle() {
      return this.title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return this.description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getThumbMediaId() {
      return this.thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
      this.thumbMediaId = thumbMediaId;
    }

    public String getMusicUrl() {
      return this.musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
      this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
      return this.hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
      this.hqMusicUrl = hqMusicUrl;
    }

  }

}
