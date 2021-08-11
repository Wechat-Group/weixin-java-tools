package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudStaticFileListResult extends WxOpenResult {

  private static final long serialVersionUID = -7323125787390678337L;

  /**
   * 文件信息
   */
  @SerializedName("contents")
  private List<Content> contents;

  /**
   * 返回内容是否被截断
   */
  @SerializedName("is_truncated")
  private boolean isTruncated;

  @Data
  public static class Content {

    /**
     * 文件名称
     */
    @SerializedName("key")
    private String key;

    /**
     * 上次修改时间
     */
    @SerializedName("last_modified")
    private String lastModified;

    /**
     * 文件的md5
     */
    @SerializedName("md5")
    private String md5;

    /**
     * 文件大小
     */
    @SerializedName("size")
    private String size;
  }

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
