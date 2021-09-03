package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;

/**
 * @author chutian0124
 */
@Data
public class Attachment implements Serializable {
  private static final long serialVersionUID = -8078748379570640198L;

  @SerializedName("msgtype")
  private String msgType;

  private Image image;

  private Link link;

  @SerializedName("miniprogram")
  private MiniProgram miniProgram;

  private Video video;

  private File file;

  public void setImage(Image image) {
    this.image = image;
    this.msgType = WxCpConsts.WelcomeMsgType.IMAGE;
  }

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
  }

  public void setFile(File file ) {
    this.file = file;
    this.msgType = WxCpConsts.WelcomeMsgType.FILE;
  }
}
