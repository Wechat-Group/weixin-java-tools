package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;

@Getter
public class Attachment implements Serializable {
  private static final long serialVersionUID = -8078748379570640198L;

  @SerializedName("msgtype")
  private final String msgType;

  @SerializedName("image")
  private final Image image;

  @SerializedName("link")
  private final Link link;

  @SerializedName("miniprogram")
  private final MiniProgram miniprogram;

  @SerializedName("video")
  private final Video video;

  @SerializedName("file")
  private final File file;

  private Attachment(String msgType, Image image, Link link, MiniProgram miniprogram, Video video, File file) {
    this.msgType = msgType;
    this.image = image;
    this.link = link;
    this.miniprogram = miniprogram;
    this.video = video;
    this.file = file;
  }


  public static Attachment image(Image image) {
    return new Attachment(WxCpConsts.AttachmentMsgType.IMAGE.getName(), image, null, null, null, null);
  }

  public static Attachment link(Link link) {
    return new Attachment(WxCpConsts.AttachmentMsgType.LINK.getName(), null, link, null, null, null);
  }

  public static Attachment miniprogram(MiniProgram miniProgram) {
    return new Attachment(WxCpConsts.AttachmentMsgType.MINIPROGRAM.getName(), null, null, miniProgram, null, null);
  }

  public static Attachment video(Video video) {
    return new Attachment(WxCpConsts.AttachmentMsgType.VIDEO.getName(), null, null, null, video, null);
  }

  public static Attachment file(File file) {
    return new Attachment(WxCpConsts.AttachmentMsgType.FILE.getName(), null, null, null, null, file);
  }
}
