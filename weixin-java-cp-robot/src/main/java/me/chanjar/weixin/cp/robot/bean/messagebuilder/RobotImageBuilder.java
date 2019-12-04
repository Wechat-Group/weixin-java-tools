package me.chanjar.weixin.cp.robot.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;
import me.chanjar.weixin.cp.robot.util.file.ImageUtil;

import java.io.File;
import java.io.IOException;

/**
 * 机器人图片消息builder
 * <pre>
 * 用法: WxCpRobotMessage m = WxCpRobotMessage.IMAGE().base64(...).md5().addMember(...).build();
 * </pre>
 *
 * @author linfeng
 */
public final class RobotImageBuilder extends RobotBuilder<RobotImageBuilder> {
  private String base64;
  private String md5;

  public RobotImageBuilder() {
    this.msgType = WxConsts.KefuMsgType.IMAGE;
  }

  public RobotImageBuilder localFile(File fileItem) throws IOException {
    ImageUtil.RobotImage robotImage = ImageUtil.convert(fileItem);
    this.base64 = robotImage.getBase64();
    this.md5 = robotImage.getMd5();
    return this;
  }

  @Override
  public WxCpRobotMessage build() {
    WxCpRobotMessage m = super.build();
    m.setImageBase64Value(this.base64);
    m.setImageMd5Value(this.md5);
    return m;
  }
}
