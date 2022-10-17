package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 文件信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("file_info")
  private FileInfo fileInfo;

  /**
   * The type File info.
   */
  @Getter
  @Setter
  public static class FileInfo implements Serializable {
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

    @SerializedName("md5")
    private String md5;

    @SerializedName("url")
    private String url;

    /**
     * From json file info.
     *
     * @param json the json
     * @return the file info
     */
    public static FileInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, FileInfo.class);
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
   * From json wx cp file info.
   *
   * @param json the json
   * @return the wx cp file info
   */
  public static WxCpFileInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
