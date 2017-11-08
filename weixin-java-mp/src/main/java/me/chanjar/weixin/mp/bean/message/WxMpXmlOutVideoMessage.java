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
public class WxMpXmlOutVideoMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = 1745902309380113978L;

  @XStreamAlias("Video")
  protected final Video video = new Video();

  public WxMpXmlOutVideoMessage() {
    this.msgType = WxConsts.XmlMsgType.VIDEO;
  }

  @XStreamAlias("Video")
  @Data
  public static class Video implements Serializable {
    private static final long serialVersionUID = -6445448977569651183L;

    @XStreamAlias("MediaId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String mediaId;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

  }

  public String getMediaId() {
    return this.video.mediaId;
  }

  public void setMediaId(String mediaId) {
    this.video.mediaId = mediaId;
  }

  public String getTitle() {
    return this.video.title;
  }

  public void setTitle(String title) {
    this.video.title = title;
  }

  public String getDescription() {
    return this.video.description;
  }

  public void setDescription(String description) {
    this.video.description = description;
  }
}
