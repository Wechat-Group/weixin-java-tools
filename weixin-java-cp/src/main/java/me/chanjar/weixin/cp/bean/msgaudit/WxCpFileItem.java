package me.chanjar.weixin.cp.bean.msgaudit;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 会话存档 文档信息对象
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileItem implements Serializable {

  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("filename")
  private String fileName;

  @SerializedName("md5sum")
  private String md5Sum;

  @SerializedName("sdkfileid")
  private String sdkFileId;

  /**
   * 共享文件的大小 Uint64类型
   */
  @SerializedName("filesize")
  private BigInteger fileSize;

  /**
   * From json wx cp file item.
   *
   * @param json the json
   * @return the wx cp file item
   */
  public static WxCpFileItem fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileItem.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
