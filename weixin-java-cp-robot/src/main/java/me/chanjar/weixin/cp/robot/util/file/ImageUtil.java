package me.chanjar.weixin.cp.robot.util.file;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * @author linfeng-eqxiu
 * @date 2019/12/4
 */
public final class ImageUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);

  private ImageUtil() {

  }

  /**
   * 本地文件转换为机器人消息要求的编码
   *
   * @param localFile 本地文件
   * @return 机器人图片对象
   */
  public static RobotImage convert(File localFile) throws IOException {

    byte[] buffer = FileUtils.readFileToByteArray(localFile);
    String md5 = DigestUtils.md5Hex(buffer);
    String base64 = Base64.getEncoder().encodeToString(buffer);
    return new RobotImage(base64, md5);
  }

  public static class RobotImage {
    private String base64;
    private String md5;

    RobotImage(String base64, String md5) {
      this.base64 = base64;
      this.md5 = md5;
    }

    public String getBase64() {
      return base64;
    }

    public String getMd5() {
      return md5;
    }
  }

}
