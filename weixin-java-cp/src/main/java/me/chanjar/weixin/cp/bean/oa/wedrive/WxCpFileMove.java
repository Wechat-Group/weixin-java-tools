package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 移动文件返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileMove extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("file_list")
  private FileList fileList;

  /**
   * The type File list.
   */
  @Getter
  @Setter
  public static class FileList implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("item")
    private List<Item> item;

    /**
     * From json file list.
     *
     * @param json the json
     * @return the file list
     */
    public static FileList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, FileList.class);
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
   * The type Item.
   */
  @Getter
  @Setter
  public static class Item implements Serializable {
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

    /**
     * From json item.
     *
     * @param json the json
     * @return the item
     */
    public static Item fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Item.class);
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
   * From json wx cp file move.
   *
   * @param json the json
   * @return the wx cp file move
   */
  public static WxCpFileMove fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileMove.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
