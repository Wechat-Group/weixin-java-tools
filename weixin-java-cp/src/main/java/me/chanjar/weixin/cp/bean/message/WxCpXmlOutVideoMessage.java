package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;

/**
 * The type Wx cp xml out video message.
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutVideoMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = -8672761162722733622L;

  /**
   * The Video.
   */
  @XStreamAlias("Video")
  protected final Video video = new Video();

  /**
   * Instantiates a new Wx cp xml out video message.
   */
  public WxCpXmlOutVideoMessage() {
    this.msgType = WxConsts.XmlMsgType.VIDEO;
  }

  /**
   * Gets media id.
   *
   * @return the media id
   */
  public String getMediaId() {
    return this.video.getMediaId();
  }

  /**
   * Sets media id.
   *
   * @param mediaId the media id
   */
  public void setMediaId(String mediaId) {
    this.video.setMediaId(mediaId);
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return this.video.getTitle();
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.video.setTitle(title);
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.video.getDescription();
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.video.setDescription(description);
  }

  /**
   * The type Video.
   */
  @Data
  @XStreamAlias("Video")
  public static class Video implements Serializable {
    private static final long serialVersionUID = -8672761162722733622L;

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

}
