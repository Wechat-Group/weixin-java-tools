package me.chanjar.weixin.mp.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutMusicMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -4159937804975448945L;

  @XStreamAlias("Music")
  @JacksonXmlProperty(localName = "Music")
  protected final Music music = new Music();

  public WxMpXmlOutMusicMessage() {
    this.msgType = WxConsts.XmlMsgType.MUSIC;
  }

  @Data
  @XStreamAlias("Music")
  @JacksonXmlRootElement(localName = "Music")
  public static class Music implements Serializable {
    private static final long serialVersionUID = -5492592401691895334L;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "Title")
    @JacksonXmlCData
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "Description")
    @JacksonXmlCData
    private String description;

    @XStreamAlias("ThumbMediaId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "ThumbMediaId")
    @JacksonXmlCData
    private String thumbMediaId;

    @XStreamAlias("MusicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "MusicUrl")
    @JacksonXmlCData
    private String musicUrl;

    @XStreamAlias("HQMusicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "HQMusicUrl")
    @JacksonXmlCData
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
