package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;

public class Attachment implements Serializable {
  private static final long serialVersionUID = -8078748379570640198L;

  @SerializedName("msgtype")
  private String msgType;

  @SerializedName("image")
  private Image image;

  @SerializedName("link")
  private Link link;

  @SerializedName("miniprogram")
  private MiniProgram miniprogram;

  @SerializedName("video")
  private Video video;

  private Attachment(String msgType, Image image, Link link, MiniProgram miniprogram, Video video) {
    this.msgType = msgType;
    this.image = image;
    this.link = link;
    this.miniprogram = miniprogram;
    this.video = video;
  }

  public static Attachment image(Image image) {
    return new Attachment(WxCpConsts.AttachmentMsgType.IMAGE.getName(), image, null, null, null);
  }

  public static Attachment link(Link link) {
    return new Attachment(WxCpConsts.AttachmentMsgType.LINK.getName(), null, link, null, null);
  }

  public static Attachment miniprogram(MiniProgram miniProgram) {
    return new Attachment(WxCpConsts.AttachmentMsgType.MINIPROGRAM.getName(), null, null, miniProgram, null);
  }

  public static Attachment video(Video video) {
    return new Attachment(WxCpConsts.AttachmentMsgType.VIDEO.getName(), null, null, null, video);
  }

}
