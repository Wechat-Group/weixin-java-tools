package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
<<<<<<< HEAD
import lombok.Getter;
=======
import lombok.Data;
>>>>>>> af212bd68fefdb8f41d86d60be33e5ee68056cc1
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;

<<<<<<< HEAD
@Getter
=======
/**
 * @author chutian0124
 */
@Data
>>>>>>> af212bd68fefdb8f41d86d60be33e5ee68056cc1
public class Attachment implements Serializable {
  private static final long serialVersionUID = -8078748379570640198L;

  @SerializedName("msgtype")
  private final String msgType;

  @SerializedName("image")
  private final Image image;

  @SerializedName("link")
  private final Link link;

  @SerializedName("miniprogram")
<<<<<<< HEAD
  private final MiniProgram miniprogram;
=======
  private MiniProgram miniProgram;
>>>>>>> af212bd68fefdb8f41d86d60be33e5ee68056cc1

  @SerializedName("video")
  private final Video video;

<<<<<<< HEAD
  @SerializedName("file")
  private final File file;

  private Attachment(String msgType, Image image, Link link, MiniProgram miniprogram, Video video, File file) {
    this.msgType = msgType;
=======
  private File file;

  public void setImage(Image image) {
>>>>>>> af212bd68fefdb8f41d86d60be33e5ee68056cc1
    this.image = image;
    this.link = link;
    this.miniprogram = miniprogram;
    this.video = video;
    this.file = file;
  }

<<<<<<< HEAD

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
=======
  public void setLink(Link link) {
    this.link = link;
    this.msgType = WxCpConsts.WelcomeMsgType.LINK;
  }

  public void setMiniProgram(MiniProgram miniProgram) {
    this.miniProgram = miniProgram;
    this.msgType = WxCpConsts.WelcomeMsgType.MINIPROGRAM;
  }

  public void setVideo(Video video) {
    this.video = video;
    this.msgType = WxCpConsts.WelcomeMsgType.VIDEO;
>>>>>>> af212bd68fefdb8f41d86d60be33e5ee68056cc1
  }

  public void setFile(File file ) {
    this.file = file;
    this.msgType = WxCpConsts.WelcomeMsgType.FILE;
  }
}
