package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;

public class Attachment implements Serializable {
  private static final long serialVersionUID = -8078748379570640198L;

  @SerializedName("msgtype")
  private String msgType;

  private Image image;

  private Link link;

  private MiniProgram miniprogram;

  private Video video;

  @Override
  public String toString() {
    return "Attachment{" +
      "msgType='" + msgType + '\'' +
      ", image=" + image +
      ", link=" + link +
      ", miniprogram=" + miniprogram +
      ", video=" + video +
      '}';
  }

  private String getMsgType() {
    return msgType;
  }

  private void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
    this.msgType = WxCpConsts.WelcomeMsgType.IMAGE.toString();
  }

  public Link getLink() {
    return link;
  }

  public void setLink(Link link) {
    this.link = link;
    this.msgType = WxCpConsts.WelcomeMsgType.LINK.toString();
  }

  public MiniProgram getMiniprogram() {
    return miniprogram;
  }

  public void setMiniprogram(MiniProgram miniprogram) {
    this.miniprogram = miniprogram;
    this.msgType = WxCpConsts.WelcomeMsgType.MINIPROGRAM.toString();
  }

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
    this.msgType = WxCpConsts.WelcomeMsgType.VIDEO.toString();
  }
}
