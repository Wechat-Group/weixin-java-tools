package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 下载文件返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileRename extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("file")
  private File file;

  /**
   * The type File.
   */
  @Getter
  @Setter
  public static class File implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("fileid")
    private String fileId;

    @SerializedName("file_name")
    private String fileName;

    @SerializedName("spaceid")
    private String spaceId;

    @SerializedName("fatherid")
    private String fatherId;

    @SerializedName("file_size")
    private Long fileSize;

    @SerializedName("ctime")
    private Long cTime;

    @SerializedName("mtime")
    private Long mTime;

    @SerializedName("file_type")
    private Integer fileType;

    @SerializedName("file_status")
    private Integer fileStatus;

    @SerializedName("create_userid")
    private String createUserId;

    @SerializedName("update_userid")
    private String updateUserId;

    @SerializedName("sha")
    private String sha;

    @SerializedName("url")
    private String url;

    @SerializedName("md5")
    private String md5;

    /**
     * From json file.
     *
     * @param json the json
     * @return the file
     */
    public static File fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, File.class);
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

  /**
   * From json wx cp file rename.
   *
   * @param json the json
   * @return the wx cp file rename
   */
  public static WxCpFileRename fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileRename.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
